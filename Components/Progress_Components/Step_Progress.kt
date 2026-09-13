// ----------------------------------------------------
// STEP MODEL
// ----------------------------------------------------

data class ProgressStep(
    val title: String,
    val description: String,
    val icon: ImageVector
)

@Composable
fun PremiumStepProgressDemo() {

    val steps = listOf(
        ProgressStep(
            title = "Personal",
            description = "Add your personal information",
            icon = Icons.Default.Person
        ),
        ProgressStep(
            title = "Address",
            description = "Enter your delivery address",
            icon = Icons.Default.LocationOn
        ),
        ProgressStep(
            title = "Payment",
            description = "Choose your payment method",
            icon = Icons.Default.CreditCard
        ),
        ProgressStep(
            title = "Complete",
            description = "Your setup is complete!",
            icon = Icons.Default.Check
        )
    )

    var currentStep by remember {
        mutableIntStateOf(0)
    }

    val progress by animateFloatAsState(
        targetValue = currentStep.toFloat() / (steps.size - 1),
        animationSpec = tween(
            durationMillis = 500,
            easing = FastOutSlowInEasing
        ),
        label = "stepProgress"
    )

    val primaryColor = Color(0xFF6C63FF)
    val backgroundColor = Color(0xFFF6F7FB)
    val textColor = Color(0xFF171A2B)
    val secondaryText = Color(0xFF8A8FA3)
    val trackColor = Color(0xFFE8E8F2)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(30.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {

                // ------------------------------------------------
                // HEADER
                // ------------------------------------------------

                Text(
                    text = "Get Started",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Complete each step to finish your setup",
                    fontSize = 14.sp,
                    color = secondaryText
                )

                Spacer(modifier = Modifier.height(30.dp))

                // ------------------------------------------------
                // STEP PROGRESS
                // ------------------------------------------------

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                ) {

                    // Background connecting line
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 22.dp,
                                end = 22.dp,
                                top = 22.dp
                            )
                            .height(4.dp)
                            .clip(RoundedCornerShape(50))
                            .background(trackColor)
                    )

                    // Animated progress line
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(progress)
                            .padding(
                                start = 22.dp,
                                top = 22.dp
                            )
                            .height(4.dp)
                            .clip(RoundedCornerShape(50))
                            .background(primaryColor)
                    )

                    // Step circles
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        steps.forEachIndexed { index, step ->

                            val completed = index < currentStep
                            val active = index == currentStep

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Box(
                                    modifier = Modifier
                                        .size(43.dp)
                                        .clip(CircleShape)
                                        .background(
                                            when {
                                                completed || active ->
                                                    primaryColor

                                                else ->
                                                    trackColor
                                            }
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {

                                    Icon(
                                        imageVector = if (completed)
                                            Icons.Default.Check
                                        else
                                            step.icon,
                                        contentDescription = step.title,
                                        tint = if (completed || active)
                                            Color.White
                                        else
                                            secondaryText,
                                        modifier = Modifier.size(21.dp)
                                    )
                                }

                                Spacer(
                                    modifier = Modifier.height(8.dp)
                                )

                                Text(
                                    text = step.title,
                                    fontSize = 11.sp,
                                    fontWeight = if (active || completed)
                                        FontWeight.Bold
                                    else
                                        FontWeight.Medium,
                                    color = if (active || completed)
                                        primaryColor
                                    else
                                        secondaryText
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                // ------------------------------------------------
                // CURRENT STEP CARD
                // ------------------------------------------------

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF7F6FF)
                    )
                ) {

                    AnimatedContent(
                        targetState = currentStep,
                        transitionSpec = {
                            fadeIn(
                                animationSpec = tween(300)
                            ) togetherWith fadeOut(
                                animationSpec = tween(200)
                            )
                        },
                        label = "stepContent"
                    ) { stepIndex ->

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(22.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Box(
                                modifier = Modifier
                                    .size(58.dp)
                                    .clip(CircleShape)
                                    .background(
                                        Color(0xFFE9E7FF)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {

                                Icon(
                                    imageVector = steps[stepIndex].icon,
                                    contentDescription = null,
                                    tint = primaryColor,
                                    modifier = Modifier.size(28.dp)
                                )
                            }

                            Spacer(
                                modifier = Modifier.height(14.dp)
                            )

                            Text(
                                text = steps[stepIndex].title,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = textColor
                            )

                            Spacer(
                                modifier = Modifier.height(6.dp)
                            )

                            Text(
                                text = steps[stepIndex].description,
                                fontSize = 13.sp,
                                color = secondaryText,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(22.dp))

                // ------------------------------------------------
                // STEP COUNTER
                // ------------------------------------------------

                Text(
                    text = "Step ${currentStep + 1} of ${steps.size}",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = secondaryText
                )

                Spacer(modifier = Modifier.height(18.dp))

                // ------------------------------------------------
                // NAVIGATION BUTTONS
                // ------------------------------------------------

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    OutlinedButton(
                        onClick = {
                            if (currentStep > 0) {
                                currentStep--
                            }
                        },
                        enabled = currentStep > 0,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(15.dp)
                    ) {
                        Text("Back")
                    }

                    Button(
                        onClick = {
                            if (currentStep < steps.lastIndex) {
                                currentStep++
                            }
                        },
                        enabled = currentStep < steps.lastIndex,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = primaryColor
                        )
                    ) {
                        Text(
                            text = if (currentStep == steps.lastIndex)
                                "Completed"
                            else
                                "Next"
                        )
                    }
                }
            }
        }
    }
}
