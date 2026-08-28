data class BottomNavItem(
    val title: String,
    val icon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem("Home", Icons.Default.Home),
    BottomNavItem("Explore", Icons.Default.Explore),
    BottomNavItem("Favorites", Icons.Default.Favorite),
    BottomNavItem("Profile", Icons.Default.Person)
)


@Composable
fun PremiumBottomNavigationDemo() {

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    val Primary = Color(0xFF635BFF)

    Box(
        modifier = Modifier
            .fillMaxSize()
           // .background(Background)
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFEDE9FE),
                        Color(0xFFFCE7F3),
                        Color(0xFFE0F2FE)
                    )
                )
            )) {

        // Screen Content
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            AnimatedContent(
                targetState = selectedIndex,
                transitionSpec = {
                    (fadeIn(
                        animationSpec = tween(350)
                    ) + scaleIn(
                        initialScale = 0.8f,
                        animationSpec = tween(350)
                    )) togetherWith
                            (fadeOut(
                                animationSpec = tween(200)
                            ) + scaleOut(
                                targetScale = 1.15f,
                                animationSpec = tween(200)
                            ))
                },
                label = "selectedContent"
            ) { index ->

                val item = bottomNavItems[index]

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // Animated selected icon
                    Box(
                        modifier = Modifier
                            .size(92.dp)
                            .clip(CircleShape)
                            .background(
                                Primary.copy(alpha = 0.12f)
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        Box(
                            modifier = Modifier
                                .size(66.dp)
                                .clip(CircleShape)
                                .background(Primary),
                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title,
                                tint = Color.White,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(18.dp)
                    )

                    Text(
                        text = item.title,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF172033)
                    )

                    Spacer(
                        modifier = Modifier.height(5.dp)
                    )

                }
            }
        }
        PremiumBottomNavigation(
            selectedIndex = selectedIndex,
            onItemSelected = {
                selectedIndex = it
            },
            primaryColor = Primary,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 20.dp
                )
        )
    }
}
@Composable
fun PremiumBottomNavigation(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    primaryColor: Color,
    modifier: Modifier = Modifier
) {

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                bottom = 30.dp
            )
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(26.dp)
            ),
        shape = RoundedCornerShape(26.dp),
        color = Color.White,
        tonalElevation = 4.dp
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 8.dp,
                    vertical = 9.dp
                ),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            bottomNavItems.forEachIndexed { index, item ->

                PremiumNavItem(
                    item = item,
                    selected = selectedIndex == index,
                    primaryColor = primaryColor,
                    onClick = {
                        onItemSelected(index)
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
@Composable
fun PremiumNavItem(
    item: BottomNavItem,
    selected: Boolean,
    primaryColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val iconScale by animateFloatAsState(
        targetValue = if (selected) 1.08f else 1f,
        animationSpec = tween(
            durationMillis = 220,
            easing = FastOutSlowInEasing
        ),
        label = "iconScale"
    )

    val iconColor by animateColorAsState(
        targetValue = if (selected) {
            primaryColor
        } else {
            Color(0xFF9CA3AF)
        },
        animationSpec = tween(220),
        label = "iconColor"
    )

    val indicatorColor = primaryColor.copy(
        alpha = if (selected) 0.12f else 0f
    )

    Column(
        modifier = modifier
            .animateContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .clip(CircleShape)
                .clickable(
                    onClick = onClick
                )
                .background(
                    primaryColor.copy(
                        alpha = if (selected) 0.10f else 0f
                    )
                )
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = item.icon,
                contentDescription = item.title,
                tint = iconColor,
                modifier = Modifier
                    .size(24.dp)
                    .graphicsLayer {
                        scaleX = iconScale
                        scaleY = iconScale
                    }
            )
        }

        AnimatedVisibility(
            visible = selected
        ) {
            Text(
                text = item.title,
                color = primaryColor,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
      }
}


