data class RailItem(
    val title: String,
    val icon: ImageVector
)

val railItems = listOf(
    RailItem("Home", Icons.Outlined.Home),
    RailItem("Explore", Icons.Outlined.Explore),
    RailItem("Saved", Icons.Outlined.BookmarkBorder),
    RailItem("Profile", Icons.Outlined.PersonOutline)
)

@Composable
fun PremiumNavigationRailDemo() {

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    val primaryColor = Color(0xFF635BFF)

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFF7F5FF),
                        Color(0xFFEDE9FE),
                        Color(0xFFFCE7F3)
                    )
                )
            )
    ) {

        PremiumNavigationRail(
            selectedIndex = selectedIndex,
            onItemSelected = {
                selectedIndex = it
            },
            primaryColor = primaryColor
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {

            AnimatedContent(
                targetState = selectedIndex,
                transitionSpec = {
                    fadeIn(
                        animationSpec = tween(300)
                    ) togetherWith fadeOut(
                        animationSpec = tween(200)
                    )
                },
                label = "screenContent"
            ) { index ->

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        imageVector = railItems[index].icon,
                        contentDescription = null,
                        tint = primaryColor,
                        modifier = Modifier.size(64.dp)
                    )

                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    Text(
                        text = railItems[index].title,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF172033)
                    )

                    Spacer(
                        modifier = Modifier.height(5.dp)
                    )

                    Text(
                        text = "Currently selected",
                        fontSize = 12.sp,
                        color = Color(0xFF8B8798)
                    )
                }
            }
        }
    }
}
@Composable
fun PremiumNavigationRail(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    primaryColor: Color
) {

    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .padding(
                start = 18.dp,
                top = 24.dp,
                bottom = 24.dp
            )
            .width(82.dp)
            .shadow(
                elevation = 14.dp,
                shape = RoundedCornerShape(28.dp)
            ),
        shape = RoundedCornerShape(28.dp),
        color = Color.White
    ) {

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(
                    vertical = 18.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                imageVector = Icons.Outlined.Dashboard,
                contentDescription = null,
                tint = primaryColor,
                modifier = Modifier.size(28.dp)
            )

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            railItems.forEachIndexed { index, item ->

                PremiumRailItem(
                    item = item,
                    selected = selectedIndex == index,
                    primaryColor = primaryColor,
                    onClick = {
                        onItemSelected(index)
                    }
                )

                if (index < railItems.lastIndex) {
                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )
                }
            }

            Spacer(
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = Icons.Outlined.Settings,
                contentDescription = "Settings",
                tint = Color(0xFF92909C),
                modifier = Modifier
                    .size(24.dp)
                    .clickable { }
            )
        }
    }
}
@Composable
fun PremiumRailItem(
    item: RailItem,
    selected: Boolean,
    primaryColor: Color,
    onClick: () -> Unit
) {

    val scale by animateFloatAsState(
        targetValue = if (selected) 1.12f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "iconScale"
    )

    val iconColor by animateColorAsState(
        targetValue = if (selected)
            primaryColor
        else
            Color(0xFF92909C),
        animationSpec = tween(250),
        label = "iconColor"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(
                    if (selected)
                        primaryColor.copy(alpha = 0.12f)
                    else
                        Color.Transparent
                )
                .clickable(
                    onClick = onClick
                ),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = item.icon,
                contentDescription = item.title,
                tint = iconColor,
                modifier = Modifier
                    .size(24.dp)
                    .scale(scale)
            )
        }

        AnimatedVisibility(
            visible = selected,
            enter = fadeIn(
                animationSpec = tween(200)
            ) + expandVertically(
                animationSpec = tween(250)
            ),
            exit = fadeOut(
                animationSpec = tween(150)
            ) + shrinkVertically(
                animationSpec = tween(200)
            )
        ) {

            Text(
                text = item.title,
                color = primaryColor,
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                modifier = Modifier.padding(
                    top = 3.dp
                )
            )
        }
    }
}
