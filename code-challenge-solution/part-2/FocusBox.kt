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