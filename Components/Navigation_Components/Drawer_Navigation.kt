//Create Drawer Model
data class DrawerItem(
    val title: String,
    val icon: ImageVector
)
//Add Drawer Items
val drawerItems = listOf(
    DrawerItem("Home", Icons.Outlined.Home),
    DrawerItem("Explore", Icons.Outlined.Explore),
    DrawerItem("Favorites", Icons.Outlined.FavoriteBorder),
    DrawerItem("Profile", Icons.Outlined.PersonOutline)
)

@Composable
fun PremiumNavigationDrawerDemo() {
//Setup Drawer State
    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

    val scope = rememberCoroutineScope()

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    val primaryColor = Color(0xFF635BFF)

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
//Build Drawer Sheet
            PremiumDrawerContent(
                selectedIndex = selectedIndex,
                primaryColor = primaryColor,
                onItemSelected = { index ->
                    selectedIndex = index

                    scope.launch {
                        drawerState.close()
                    }
                }
            )
        }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFF7F5FF),
                            Color(0xFFEDE9FE),
                            Color(0xFFFCE7F3),
                            Color(0xFFE0F2FE)
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                // Top Bar

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 20.dp,
                            end = 20.dp,
                            top = 20.dp,
                            bottom = 12.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
//Add Menu Button
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .clickable {
                                scope.launch {
                                    drawerState.open()
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Open Drawer",
                            tint = primaryColor,
                            modifier = Modifier.size(25.dp)
                        )
                    }

                    Spacer(
                        modifier = Modifier.width(14.dp)
                    )

                    Column {

                        Text(
                            text = "Welcome back",
                            fontSize = 12.sp,
                            color = Color(0xFF858090)
                        )

                        Text(
                            text = drawerItems[selectedIndex].title,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF171326)
                        )
                    }
                }

                // Main Content
               // Animate Screen Content
                AnimatedContent(
                    targetState = selectedIndex,
                    transitionSpec = {
                        fadeIn(
                            animationSpec = tween(350)
                        ) togetherWith fadeOut(
                            animationSpec = tween(200)
                        )
                    },
                    label = "drawerContent"
                ) { index ->

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Box(
                            modifier = Modifier
                                .size(120.dp)
                                .clip(
                                    RoundedCornerShape(36.dp)
                                )
                                .background(
                                    Brush.linearGradient(
                                        colors = listOf(
                                            primaryColor,
                                            Color(0xFF9B5DE5),
                                            Color(0xFFF15BB5)
                                        )
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                imageVector = drawerItems[index].icon,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(52.dp)
                            )
                        }

                        Spacer(
                            modifier = Modifier.height(24.dp)
                        )

                        Text(
                            text = drawerItems[index].title,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF171326)
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text(
                            text = "Explore your ${drawerItems[index].title.lowercase()}",
                            fontSize = 14.sp,
                            color = Color(0xFF858090)
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun PremiumDrawerContent(
    selectedIndex: Int,
    primaryColor: Color,
    onItemSelected: (Int) -> Unit
) {

    ModalDrawerSheet(
        modifier = Modifier
            .width(310.dp),
        drawerContainerColor = Color.White,
        drawerShape = RoundedCornerShape(
            topEnd = 32.dp,
            bottomEnd = 32.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxHeight()
        ) {

//Design Profile Header

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF635BFF),
                                Color(0xFF8B5CF6),
                                Color(0xFFEC4899)
                            )
                        ),
                    )
                    .padding(24.dp)
            ) {

                Column(
                    verticalArrangement = Arrangement.Bottom
                ) {

                    Box(
                        modifier = Modifier
                            .size(58.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.25f)),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Text(
                        text = "Alex Morgan",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "alex@example.com",
                        color = Color.White.copy(alpha = 0.80f),
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            Text(
                text = "MENU",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF9A96A5),
                modifier = Modifier.padding(
                    start = 24.dp,
                    bottom = 10.dp
                )
            )
            //Create Drawer Items
            drawerItems.forEachIndexed { index, item ->

                PremiumDrawerItem(
                    item = item,
                    selected = selectedIndex == index,
                    primaryColor = primaryColor,
                    onClick = {
                        //Connect Navigation
                        onItemSelected(index)
                    }
                )
            }

            Spacer(
                modifier = Modifier.weight(1f)
            )

            HorizontalDivider(
                modifier = Modifier.padding(
                    horizontal = 24.dp
                ),
                color = Color(0xFFEDEAF2)
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )
//Add Bottom Actions
            PremiumDrawerBottomItem(
                icon = Icons.Outlined.Settings,
                title = "Settings",
                primaryColor = primaryColor
            )

            PremiumDrawerBottomItem(
                icon = Icons.Outlined.Logout,
                title = "Logout",
                primaryColor = Color(0xFFEF476F)
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )
        }
    }
}
@Composable
fun PremiumDrawerItem(
    item: DrawerItem,
    selected: Boolean,
    primaryColor: Color,
    onClick: () -> Unit
) {
//Animate Selected Item
    val iconScale by animateFloatAsState(
        targetValue = if (selected) 1.1f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "drawerIconScale"
    )

    val iconColor by animateColorAsState(
        targetValue = if (selected) {
            primaryColor
        } else {
            Color(0xFF858090)
        },
        animationSpec = tween(250),
        label = "drawerIconColor"
    )

    val textColor by animateColorAsState(
        targetValue = if (selected) {
            primaryColor
        } else {
            Color(0xFF4B4755)
        },
        animationSpec = tween(250),
        label = "drawerTextColor"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 14.dp,
                vertical = 4.dp
            )
            .clip(RoundedCornerShape(18.dp))
            .background(
                if (selected)
                    primaryColor.copy(alpha = 0.10f)
                else
                    Color.Transparent
            )
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 14.dp,
                vertical = 12.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = item.icon,
            contentDescription = item.title,
            tint = iconColor,
            modifier = Modifier
                .size(23.dp)
                .scale(iconScale)
        )

        Spacer(
            modifier = Modifier.width(16.dp)
        )

        Text(
            text = item.title,
            color = textColor,
            fontSize = 14.sp,
            fontWeight = if (selected)
                FontWeight.Bold
            else
                FontWeight.Medium
        )

        Spacer(
            modifier = Modifier.weight(1f)
        )
//Add Selected Indicator
        AnimatedVisibility(
            visible = selected,
            enter = fadeIn() + scaleIn(),
            exit = fadeOut() + scaleOut()
        ) {

            Box(
                modifier = Modifier
                    .size(7.dp)
                    .clip(CircleShape)
                    .background(primaryColor)
            )
        }
    }
}
@Composable
fun PremiumDrawerBottomItem(
    icon: ImageVector,
    title: String,
    primaryColor: Color
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(
                horizontal = 28.dp,
                vertical = 12.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = primaryColor,
            modifier = Modifier.size(22.dp)
        )

        Spacer(
            modifier = Modifier.width(16.dp)
        )

        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF4B4755)
        )
    }
}
