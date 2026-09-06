// STEP 1: Create Navigation Item Model
data class FloatingNavItem(
    val title: String,
    val icon: ImageVector
)

// STEP 2: Add Navigation Items
val floatingNavItems = listOf(
    FloatingNavItem("Home", Icons.Outlined.Home),
    FloatingNavItem("Explore", Icons.Outlined.Explore),
    FloatingNavItem("Saved", Icons.Outlined.BookmarkBorder),
    FloatingNavItem("Profile", Icons.Outlined.PersonOutline)
)

@Composable
fun PremiumFloatingActionNavigationDemo() {
    //Track Selected Item
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    // STEP 4: Track FAB Expanded State
    var expanded by remember {
        mutableStateOf(false)
    }

    val primaryColor = Color(0xFF635BFF)

    Box(
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

        // Main Content
        // STEP 10: Animate Screen Content

        AnimatedContent(
            targetState = selectedIndex,
            transitionSpec = {
                fadeIn(
                    animationSpec = tween(300)
                ) + scaleIn(
                    initialScale = 0.92f,
                    animationSpec = tween(300)
                ) togetherWith
                        fadeOut(
                            animationSpec = tween(180)
                        )
            },
            label = "screenContent"
        ) { index ->

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(
                            Brush.linearGradient(
                                colors = listOf(
                                    primaryColor,
                                    Color(0xFF8B5CF6),
                                    Color(0xFFEC4899)
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = floatingNavItems[index].icon,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(44.dp)
                    )
                }

                Spacer(
                    modifier = Modifier.height(20.dp)
                )
                Text(
                    text = floatingNavItems[index].title,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF171326)
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = "Selected destination",
                    fontSize = 13.sp,
                    color = Color(0xFF858090)
                )
            }
        }


        // STEP 5: Build Floating Navigation
        PremiumFloatingNavigation(
            expanded = expanded,
            selectedIndex = selectedIndex,
            primaryColor = primaryColor,
            // STEP 6: Control Navigation Expansion
            onExpand = {
                expanded = !expanded
            },

            // STEP 11: Handle Item Clicks
            onItemSelected = { index ->
                selectedIndex = index
                expanded = false
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    end = 24.dp,
                    bottom = 28.dp
                )
        )
    }
}
// STEP 5: Create Floating Navigation Layout
@Composable
fun PremiumFloatingNavigation(
    expanded: Boolean,
    selectedIndex: Int,
    primaryColor: Color,
    onExpand: () -> Unit,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // STEP 7: Animate Navigation Items
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn() + slideInVertically(
                initialOffsetY = { it / 2 }
            ),
            exit = fadeOut() + slideOutVertically(
                targetOffsetY = { it / 2 }
            )
        ) {

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                floatingNavItems.forEachIndexed { index, item ->

                    // STEP 8: Create Individual Navigation Item
                    FloatingNavigationItem(
                        item = item,
                        selected = selectedIndex == index,
                        primaryColor = primaryColor,
                        onClick = {
                            onItemSelected(index)
                        }
                    )
                }
            }
        }

        // Main FAB
        // STEP 9: Animate FAB Rotation
        val rotation by animateFloatAsState(
            targetValue = if (expanded) 45f else 0f,
            animationSpec = tween(300),
            label = "fabRotation"
        )

        FloatingActionButton(
            onClick = onExpand,
            modifier = Modifier
                .size(64.dp)
                .graphicsLayer {
                    rotationZ = rotation
                },
            shape = CircleShape,
            containerColor = primaryColor,
            contentColor = Color.White,
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 16.dp
            )
        ) {

            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Open Navigation",
                modifier = Modifier.size(28.dp)
            )
        }
    }
}
// STEP 8: Create Reusable Navigation Item
@Composable
fun FloatingNavigationItem(
    item: FloatingNavItem,
    selected: Boolean,
    primaryColor: Color,
    onClick: () -> Unit
) {

    val scale by animateFloatAsState(
        targetValue = if (selected) 1.08f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "itemScale"
    )

    val iconColor by animateColorAsState(
        targetValue = if (selected)
            Color.White
        else
            Color(0xFF625D70),
        animationSpec = tween(220),
        label = "itemColor"
    )

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
       // Animate Item Visibility
        AnimatedVisibility(
            visible = selected,
            enter = fadeIn() + expandHorizontally(),
            exit = fadeOut() + shrinkHorizontally()
        ) {

            Surface(
                shape = RoundedCornerShape(14.dp),
                color = Color.White,
                shadowElevation = 5.dp
            ) {
        // STEP 9: Show Selected Item Label
                Text(
                    text = item.title,
                    color = primaryColor,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        horizontal = 14.dp,
                        vertical = 9.dp
                    )
                )
            }
        }

        Spacer(
            modifier = Modifier.width(10.dp)
        )

        Box(
            modifier = Modifier
                .size(48.dp)
                .scale(scale)
                .clip(CircleShape)
                .background(
                    if (selected)
                        primaryColor
                    else
                        Color.White
                )
                .clickable {
                    onClick()
                },
            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = item.icon,
                contentDescription = item.title,
                tint = iconColor,
                modifier = Modifier.size(22.dp)
            )
        }
    }
}
