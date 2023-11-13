// Coding challenge: Animate the lily and lily pad by making it rotate gently. Tip look at the animation of the breath box to get an idea

// Solution:
val lilyTransition = rememberInfiniteTransition(label = "focus transition")
    val focusRotate by lilyTransition.animateFloat(
        initialValue = 0f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(4_000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "Lily rotate "
    )

 rotate(focusRotate) {
     lilyPad()
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
 }

// Alternative solution: This solution doesn't require the draw section to be redrawn for the rotation
val lilyTransition = rememberInfiniteTransition(label = "focus transition")
    val focusRotate by lilyTransition.animateFloat(
        initialValue = 0f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(4_000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "Lily rotate "
    )
Box(
        modifier
            .fillMaxSize()
            .background(Pond3)
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
                .graphicsLayer {
                    rotationZ = focusRotate // <====== This is the modifier that does the animation
                }
                .drawBehind {
                    drawCircle(
                        color = Ripple1,
                        center = rippleOffset,
                        radius = rippleRadius.value,
                        style = Stroke(width = 1.dp.toPx())
                    )
                    val sizedLily = sizedLilyCache.getOrPut(size) {
                        val matrix = calculateMatrix(width = size.width, height = size.height)
                        RoundedPolygon(petals).apply { transform(matrix) }
                    }
                    val sizedCrown = sizedLilyCrownCache.getOrPut(size) {
                        val matrix = calculateMatrix(width = size.width, height = size.height)
                        RoundedPolygon(crown).apply { transform(matrix) }
                    }
                    lilyPad()
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
                }
)