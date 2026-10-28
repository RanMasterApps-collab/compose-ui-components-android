private val Background = Color(0xFF0B1020)
private val CardColor = Color(0xFF151D32)
private val Purple = Color(0xFF8B5CF6)
private val Pink = Color(0xFFEC4899)
private val Cyan = Color(0xFF22D3EE)
private val White = Color(0xFFF8FAFC)
private val Muted = Color(0xFF94A3B8)

@Composable
fun AnimationPlayground() {
    var showFade by remember { mutableStateOf(true) }
    var showSlide by remember { mutableStateOf(false) }
    var scaleActive by remember { mutableStateOf(false) }
    var rotationActive by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val bounceY = remember { Animatable(0f) }

    val infiniteTransition = rememberInfiniteTransition(
        label = "rotation"
    )

    val continuousRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2500,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "continuousRotation"
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF171331),
                        Background,
                        Color(0xFF0B1020)
                    )
                )
            ),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(
            horizontal = 20.dp,
            vertical = 28.dp
        ),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        item {
            HeaderSection()
        }

        // 1. Fade In & Fade Out
        item {
            AnimationCard(
                number = "01",
                title = "Fade In & Fade Out",
                subtitle = "Smooth visibility transition"
            ) {
                AnimatedVisibility(
                    visible = showFade,
                    enter = fadeIn(
                        animationSpec = tween(700)
                    ) + scaleIn(
                        initialScale = 0.85f,
                        animationSpec = tween(700)
                    ),
                    exit = fadeOut(
                        animationSpec = tween(400)
                    )
                ) {
                    GradientPreviewCard(
                        emoji = "✨",
                        title = "Premium Experience",
                        subtitle = "Beautifully animated content"
                    )
                }

                Spacer(Modifier.height(14.dp))

                ActionButton(
                    text = if (showFade) {
                        "Hide Card"
                    } else {
                        "Show Card"
                    },
                    onClick = {
                        showFade = !showFade
                    }
                )
            }
        }

        // 2. Slide In & Slide Out
        item {
            AnimationCard(
                number = "02",
                title = "Slide In & Slide Out",
                subtitle = "Notification entrance animation"
            ) {
                AnimatedVisibility(
                    visible = showSlide,
                    enter = slideInVertically(
                        initialOffsetY = { height -> height },
                        animationSpec = tween(600)
                    ) + fadeIn(tween(600)),
                    exit = slideOutVertically(
                        targetOffsetY = { height -> height },
                        animationSpec = tween(400)
                    ) + fadeOut(tween(400))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(18.dp))
                            .background(Color(0xFF202B45))
                            .border(
                                1.dp,
                                Cyan.copy(alpha = 0.35f),
                                RoundedCornerShape(18.dp)
                            )
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(46.dp)
                                .clip(CircleShape)
                                .background(Cyan.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("✓", color = Cyan, fontSize = 25.sp)
                        }

                        Spacer(Modifier.width(12.dp))

                        Column(Modifier.weight(1f)) {
                            Text(
                                "Task Completed!",
                                color = White,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                "Your changes have been saved.",
                                color = Muted,
                                fontSize = 12.sp
                            )
                        }

                        Text("NOW", color = Cyan, fontSize = 10.sp)
                    }
                }

                Spacer(Modifier.height(14.dp))

                ActionButton(
                    text = if (showSlide) {
                        "Dismiss Notification"
                    } else {
                        "Show Notification"
                    },
                    onClick = {
                        showSlide = !showSlide
                    }
                )
            }
        }

                // 3. Scale Animation
                item {
                    AnimationCard(
                        number = "03",
                        title = "Scale Animation",
                        subtitle = "Interactive zoom effect"
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(110.dp)
                                    .scale(
                                        if (scaleActive) 1.22f else 1f
                                    )
                                    .clip(RoundedCornerShape(26.dp))
                                    .background(
                                        Brush.linearGradient(
                                            listOf(Purple, Pink)
                                        )
                                    )
                                    .clickable {
                                        scaleActive = !scaleActive
                                    },
                                contentAlignment = Alignment.Center
                           ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text("💎", fontSize = 34.sp)
                                    Text(
                                        "PREMIUM",
                                        color = White,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }

                        Text(
                            "Tap the premium card to zoom",
                            color = Muted,
                            fontSize = 12.sp,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )

                        Spacer(Modifier.height(12.dp))

                        ActionButton(
                            text = if (scaleActive) "Reset Scale" else "Scale Up",
                            onClick = {
                                scaleActive = !scaleActive
                            }
                        )
                    }
                }

              // 4. Rotation Animation
              item {
                  AnimationCard(
                      number = "04",
                      title = "Rotation Animation",
                      subtitle = "Animated settings icon"
                  ) {
                      Box(
                          modifier = Modifier
                              .fillMaxWidth()
                              .height(130.dp),
                          contentAlignment = Alignment.Center
                      ) {
                          Box(
                              modifier = Modifier
                                  .size(90.dp)
                                  .rotate(
                                      if (rotationActive) {
                                          continuousRotation
                                      } else {
                                          0f
                                      }
                                  )
                                  .clip(RoundedCornerShape(25.dp))
                                  .background(
                                      Brush.linearGradient(
                                          listOf(
                                              Color(0xFF2563EB),
                                              Purple
                                          )
                                      )
                                  ),
                              contentAlignment = Alignment.Center
                          ) {
                              Text("⚙", fontSize = 48.sp, color = White)
                          }
                      }

                      ActionButton(
                          text = if (rotationActive) {
                              "Stop Rotation"
                          } else {
                              "Start Rotation"
                          },
                          onClick = {
                              rotationActive = !rotationActive
                          }
                      )
                  }
              }

              // 5. Bounce Animation
              item {
                  AnimationCard(
                      number = "05",
                      title = "Bounce Animation",
                      subtitle = "Spring-based floating button"
                  ) {
                      Box(
                          modifier = Modifier
                              .fillMaxWidth()
                              .height(150.dp),
                          contentAlignment = Alignment.Center
                      ) {
                          Box(
                              modifier = Modifier
                                  .offset {
                                      IntOffset(
                                          x = 0,
                                          y = bounceY.value.roundToInt()
                                      )
                                  }
                                  .size(76.dp)
                                  .clip(CircleShape)
                                  .background(
                                      Brush.linearGradient(
                                          listOf(Pink, Purple)
                                      )
                                  )
                                  .clickable {
                                      scope.launch {
                                          bounceY.snapTo(0f)

                                          bounceY.animateTo(
                                              targetValue = -65f,
                                              animationSpec = spring(
                                                  dampingRatio = 0.28f,
                                                  stiffness = 350f
                                              )
                                          )

                                          bounceY.animateTo(
                                              targetValue = 0f,
                                              animationSpec = spring(
                                                  dampingRatio = 0.35f,
                                                  stiffness = 300f
                                              )
                                          )
                                      }
                                  },
                              contentAlignment = Alignment.Center
                          ) {
                              Text("↑", color = White, fontSize = 38.sp)
                          }
                      }

                      Text(
                          "Tap the button to see the bounce effect",
                          color = Muted,
                          fontSize = 12.sp,
                          modifier = Modifier.fillMaxWidth(),
                          textAlign = TextAlign.Center
                      )

                      Spacer(Modifier.height(12.dp))

                      ActionButton(
                          text = "Play Bounce Animation",
                          onClick ={
                              scope.launch {
                                  bounceY.snapTo(0f)

                                  bounceY.animateTo(
                                      targetValue = -65f,
                                      animationSpec = spring(
                                          dampingRatio = 0.28f,
                                          stiffness = 350f
                                      )
                                  )

                                  bounceY.animateTo(
                                      targetValue = 0f,
                                      animationSpec = spring(
                                          dampingRatio = 0.35f,
                                          stiffness = 300f
                                      )
                                  )
                              }
                          }
                      )
                  }
              }

              item {
                  FooterSection()
              }
    }
}

@Composable
fun HeaderSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(
                        Brush.linearGradient(
                            listOf(Purple, Pink)
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text("✦", color = White, fontSize = 28.sp)
            }

            Spacer(Modifier.width(13.dp))

            Column {
                Text(
                    "ANIMATION LAB",
                    color = Cyan,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )

                Text(
                    "Compose Playground",
                    color = White,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(Modifier.height(25.dp))

        Text(
            "Bring Your UI",
            color = White,
            fontSize = 34.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Text(
            "To Life.",
            color = Purple,
            fontSize = 34.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(Modifier.height(10.dp))

        Text(
            "Explore beautiful animations built with " +
                    "Kotlin and Jetpack Compose.",
            color = Muted,
            fontSize = 14.sp,
            lineHeight = 22.sp
        )

        Spacer(Modifier.height(17.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            InfoChip("5 Animations")
            InfoChip("Material 3")
            InfoChip("Interactive")
        }
    }
}

@Composable
fun AnimationCard(
    number: String,
    title: String,
    subtitle: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(
                Brush.linearGradient(
                    listOf(
                        Color(0xFF1A2440),
                        CardColor
                    )
                )
            )
            .border(
                width = 1.dp,
                color = Color(0xFF303B58),
                shape = RoundedCornerShape(24.dp)
            )
            .padding(18.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(RoundedCornerShape(13.dp))
                    .background(Purple.copy(alpha = 0.18f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    number,
                    color = Color(0xFFC4B5FD),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            Spacer(Modifier.width(12.dp))

            Column {
                Text(
                    title,
                    color = White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(3.dp))

                Text(
                    subtitle,
                    color = Muted,
                    fontSize = 12.sp
                )
            }
        }

        Spacer(Modifier.height(22.dp))

        content()
    }
}

@Composable
fun GradientPreviewCard(
    emoji: String,
    title: String,
    subtitle: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.linearGradient(
                    listOf(
                        Color(0xFF4338CA),
                        Color(0xFF7C3AED),
                        Color(0xFFBE185D)
                    )
                )
            )
            .padding(18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White.copy(alpha = 0.16f)),
            contentAlignment = Alignment.Center
        ) {
            Text(emoji, fontSize = 28.sp)
        }

        Spacer(Modifier.width(13.dp))

        Column {
            Text(
                title,
                color = White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(5.dp))

            Text(
                subtitle,
                color = White.copy(alpha = 0.8f),
                fontSize = 11.sp
            )
        }
    }
}

@Composable
fun ActionButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Purple,
            contentColor = White
        )
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun InfoChip(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .background(Color.White.copy(alpha = 0.06f))
            .border(
                1.dp,
                Color.White.copy(alpha = 0.1f),
                CircleShape
            )
            .padding(
                horizontal = 11.dp,
                vertical = 8.dp
            )
    ) {
        Text(
            text = text,
            color = Color(0xFFCBD5E1),
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun FooterSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "BUILT WITH JETPACK COMPOSE",
            color = Cyan,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )

        Spacer(Modifier.height(8.dp))

        Text(
            "Create • Animate • Inspire",
            color = Muted,
            fontSize = 13.sp
        )
    }
}
