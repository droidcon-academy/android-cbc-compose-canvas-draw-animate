// Not running state - Shadow of the sun
@Composable
fun ShadowSun(modifier: Modifier) {
    Box(
        modifier
            .fillMaxSize()
            .background(Start)
            .drawBehind {
                drawCircle(Ripple1, size.width / 5, center = center, style = Stroke(2.0f))
            })

}

// Basic Running state - Sky and sun
@Composable
fun SkySun(modifier: Modifier) {
    Canvas(
        modifier
            .fillMaxSize()
            .background(Sky)
    ) {
        drawCircle(color = Sun2, radius = size.width / 5, center = center)
    }
}

// Gradient brushes
val skyBrush = Brush.radialGradient(listOf(Cloud, Sky))
val sunBrush = Brush.radialGradient(listOf(Sun2, Sun1))

// Basic running state with brushes - Sky and Sun with gradient
@Composable
fun SkySun(modifier: Modifier) {
    Canvas(
        modifier
            .fillMaxSize()
            .background(skyBrush)
    ) {
        drawCircle(brush = sunBrush, radius = size.width / 5, center = center)
    }
}

// animation for pulsing Sun
val infiniteTransition = rememberInfiniteTransition(label = "breath transition")
    val breathPulse by infiniteTransition.animateFloat(
        initialValue = 0.75f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(4_000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "sun size"
    )

// apply animation to Sun
@Composable
fun SkySun(modifier: Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "breath transition")
    val breathPulse by infiniteTransition.animateFloat(
        initialValue = 0.75f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(4_000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "sun size"
    )
    Canvas(
        modifier
            .fillMaxSize()
            .background(skyBrush)
    ) {
        scale(scaleX = breathPulse, scaleY = breathPulse) {
            drawCircle(brush = sunBrush, radius = size.width / 5, center = center)
        }
    }
}

// Cross fade from from not running to running
@Composable
fun BreathBox(modifier: Modifier = Modifier, isRunning: Boolean = true) {
    Crossfade(
        targetState = isRunning,
        label = "BreathBox cross fade",
        animationSpec = tween(1_000, easing = FastOutSlowInEasing)
    ) {
        if (it) {
            SkySun(modifier)
        } else {
            ShadowSun(modifier = modifier)
        }
    }
}