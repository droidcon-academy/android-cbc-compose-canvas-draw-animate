//======================== Lily pad
// Basic LilyPad
fun DrawScope.lilyPad(
    color: Color = LilyPad2,
    colorShadow: Color = Pond1,
    style: DrawStyle = Fill
) {
    val diameter = size.width * 0.6f
    translate(
        left = center.x - diameter / 2,
        top = center.y - diameter / 2
    ) {
        drawArc(
            color = color,
            startAngle = 0f,
            sweepAngle = 330f,
            useCenter = true,
            style = style,
            size = Size(diameter, diameter)
        )
    }
}

// Lily pad with shadow
        drawArc(
            color = colorShadow,
            startAngle = 0f,
            sweepAngle = 330f,
            useCenter = true,
            style = style,
            size = Size(diameter + 10, diameter + 10)
        )

//========================
// Focus running with lily pad
@Composable
private fun FocusRunning(modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxSize()
            .background(Pond3)
            .drawBehind {
                lilyPad()
            }
    )
}

// Focus not running
@Composable
private fun FocusNotRunning(modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxSize()
            .background(Start)
            .drawBehind {
                lilyPad(
                    color = Ripple1,
                    colorShadow = Color.Transparent,
                    style = Stroke(width = 2f)
                )
            }
    )
}

// Basic focus box component
@Composable
fun FocusBox(modifier: Modifier = Modifier, isRunning: Boolean = true) {
    if (isRunning) {
        FocusRunning(modifier)
    } else {
        FocusNotRunning(modifier)
    }
}

//======================== Lily
// Create flower brushes
val lilyBrush = Brush.radialGradient(listOf(Lily2, Lily1))
val lilyCoreBrush = Brush.radialGradient(listOf(LilyCore2, LilyCore1))

// Create flower shapes and paths
val petals = RoundedPolygon.star(
    numVerticesPerRadius = 12,
    innerRadius = 0.5f
)
val crown = RoundedPolygon.star(
    numVerticesPerRadius = 16,
    innerRadius = .8f,
    innerRounding = CornerRounding(radius = 0.1f)
)

// Function to calculate transformation matrix from the unit shapes

// by default the library creates canonical shapes with a radius of 1 around a center at (0, 0)
// https://medium.com/androiddevelopers/the-shape-of-things-to-come-1c7663d9dbc0
private fun calculateMatrix(
    bounds: RectF = RectF(-1f, -1f, 1f, 1f),
    width: Float,
    height: Float
): Matrix {
    val originalWidth = bounds.right - bounds.left
    val originalHeight = bounds.bottom - bounds.top
    val scale = min(width / originalWidth, height / originalHeight)
    val newLeft = bounds.left - (width / scale - originalWidth) / 2
    val newTop = bounds.top - (height / scale - originalHeight) / 2
    val matrix = Matrix()
    matrix.setTranslate(-newLeft, -newTop)
    matrix.postScale(scale, scale)
    return matrix
}

// Caching the flower paths
    val sizedLilyCache = remember(petals) {
        mutableMapOf<Size, RoundedPolygon>()
    }
    val sizedLilyCrownCache = remember(crown) {
        mutableMapOf<Size, RoundedPolygon>()
    }

// Store the flower paths and drawing
        .drawBehind {
            val sizedLily = sizedLilyCache.getOrPut(size) {
                val matrix = calculateMatrix(width = size.width, height = size.height)
                RoundedPolygon(petals).apply { transform(matrix) }
            }
            val sizedCrown = sizedLilyCrownCache.getOrPut(size) {
                val matrix = calculateMatrix(width = size.width, height = size.height)
                RoundedPolygon(crown).apply { transform(matrix) }
            }
            lilyPad()
            drawPath(
                path = sizedLily
                    .toPath()
                    .asComposePath(),
                brush = lilyBrush
            )
            drawPath(
                path = sizedCrown
                    .toPath()
                    .asComposePath(),
                brush = lilyCoreBrush
            )
            
        }

// Resizing again
translate(left = -70f, top = -70f) {
    scale(scaleX = 0.75f, scaleY = 0.75f) {
        drawPath(
            path = sizedLily
                .toPath()
                .asComposePath(),
            brush = lilyBrush
        )
    }
    scale(scaleX = 0.2f, scaleY = 0.2f) {
        drawPath(
            path = sizedCrown
                .toPath()
                .asComposePath(),
            brush = lilyCoreBrush
        )
    }
}

//======================== Ripple
// Store ripple offset
    var rippleOffset by remember { mutableStateOf(Offset.Zero) }
        
// Detect ripple offset from touch input
        // add to modifier
        .pointerInput(Unit) {
            detectTapGestures { offset ->
                // remember the offset and start the ripple
                rippleOffset = offset
            }
        }

// Draw ripple in drawBehind modifier
drawCircle(
    color = Ripple1,
    center = rippleOffset,
    radius = 100.dp.toPx(),
    style = Stroke(width = 1.dp.toPx())
)

// Store the ripple radius
val rippleRadius = remember { Animatable(1f) }

// Draw ripple in drawBehind with stored radius
drawCircle(
    color = Ripple1,
    center = rippleOffset,
    radius = rippleRadius.value,
    style = Stroke(width = 1.dp.toPx())
)

// Animate Ripple radius
val scope = rememberCoroutineScope()
// add to the modifier
    .pointerInput(Unit) {
        detectTapGestures { offset ->
            // remember the offset and start the ripple
            rippleOffset = offset
            scope.launch {
                rippleRadius.snapTo(50f)
                rippleRadius.animateTo(10000f, animationSpec = tween(10000))
            }
        }
    }

//======================== Final touch
// Cross fade running to not running
@Composable
fun FocusBox(modifier: Modifier = Modifier, isRunning: Boolean = true) {
    Crossfade(
        targetState = isRunning,
        label = "Crossfade focus",
        animationSpec = tween(1_000, easing = FastOutSlowInEasing)
    ) {
        if (it) {
            FocusRunning(modifier)
        } else {
            FocusNotRunning(modifier)
        }
    }
}
