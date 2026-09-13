@Composable
fun PremiumCircularProgressDemo() {

    var progress by remember {
        mutableFloatStateOf(0.0f)
    }

    var isProcessing by remember {
        mutableStateOf(false)
    }

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(
            durationMillis = 800,
            easing = FastOutSlowInEasing
        ),
        label = "circularProgress"
    )

    LaunchedEffect(isProcessing) {

        if (isProcessing) {

            progress = 0f

            while (progress < 1f) {
                delay(40)
                progress = (progress + 0.01f).coerceAtMost(1f)
            }

            isProcessing = false
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F7FB)),
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
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
                    .padding(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(R.drawable.circulr),
                    contentDescription = "Upload illustration",
                    modifier = Modifier
                        .size(150.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "Processing",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF171A2B)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Box(
                    modifier = Modifier.size(150.dp),
                    contentAlignment = Alignment.Center
                ) {

                    CircularProgressIndicator(
                        progress = { progress },
                        modifier = Modifier.fillMaxSize(),
                        color = Color(0xFF6C63FF),
                        trackColor = Color(0xFFE9E8FF),
                        strokeWidth = 14.dp,
                        strokeCap = StrokeCap.Round
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "${(progress * 100).toInt()}%",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF171A2B)
                        )

                        Text(
                            text = if (isProcessing)
                                "Processing..."
                            else
                                "Ready",
                            fontSize = 13.sp,
                            color = Color(0xFF8A8FA3)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Please wait while we process your task.",
                    fontSize = 14.sp,
                    color = Color(0xFF8A8FA3),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(22.dp))

                Button(
                    onClick = {
                        if (!isProcessing) {
                            isProcessing = true
                        }
                    },
                    enabled = !isProcessing,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6C63FF)
                    )
                ) {

                    Text(
                        text = if (isProcessing)
                            "Processing..."
                        else
                            "Start Processing"
                    )
                }
            }
        }
    }
