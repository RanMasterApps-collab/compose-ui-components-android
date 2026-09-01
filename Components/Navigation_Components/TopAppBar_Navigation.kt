@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarNavigationDemo() {

    // Navigation controller manages screen navigation
    val navController = rememberNavController()

    // Observe the current navigation destination
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    // Dynamic title based on the current screen
    val title = when (currentRoute) {
        "home" -> "Home"
        "explore" -> "Explore"
        "profile" -> "Profile"
        else -> "Navigation"
    }

    Scaffold(
        containerColor = Color(0xFFF8FAFC),

        topBar = {

            // Premium rounded Top App Bar
            CenterAlignedTopAppBar(

                title = {
                    AnimatedContent(
                        targetState = title,
                        label = "Top Bar Title"
                    ) { screenTitle ->

                        Text(
                            text = screenTitle,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF172554)
                        )
                    }
                },

                // Navigation Component back button
                navigationIcon = {

                    if (currentRoute != "home") {

                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {

                            Icon(
                                imageVector =
                                    Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color(0xFF2563EB)
                            )
                        }
                    }
                },

                // Top App Bar action icons
                actions = {

                    IconButton(
                        onClick = {
                            navController.navigate("explore")
                        }
                    ) {

                        Icon(
                            imageVector = Icons.Outlined.Explore,
                            contentDescription = "Explore",
                            tint = Color(0xFF7C3AED)
                        )
                    }

                    IconButton(
                        onClick = {
                            navController.navigate("profile")
                        }
                    ) {

                        Icon(
                            imageVector = Icons.Outlined.AccountCircle,
                            contentDescription = "Profile",
                            tint = Color(0xFFEC4899)
                        )
                    }
                },

                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFEFF6FF)
                )
            )
        }
    ) { paddingValues ->

        // Navigation content
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {

            // Home destination
            composable("home") {
                NavigationScreen(
                    title = "Navigation Component",
                    description = "Top App Bar + Navigation"
                )
            }

            // Explore destination
            composable("explore") {
                NavigationScreen(
                    title = "Explore",
                    description = "Navigate between screens"
                )
            }

            // Profile destination
            composable("profile") {
                NavigationScreen(
                    title = "Profile",
                    description = "Navigation destination"
                )
            }
        }
    }
}
@Composable
fun NavigationScreen(
    title: String,
    description: String
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(50.dp))

        // Main colorful illustration area
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(
                    color = Color(0xFFE0F2FE),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = Icons.Outlined.Navigation,
                contentDescription = null,
                modifier = Modifier.size(70.dp),
                tint = Color(0xFF2563EB)
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = title,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF172554)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = description,
            fontSize = 15.sp,
            color = Color(0xFF64748B)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Small topic highlight card
        Surface(
            shape = RoundedCornerShape(50.dp),
            color = Color(0xFFF3E8FF)
        ) {

            Text(
                text = "TOP APP BAR • NAVIGATION",
                modifier = Modifier.padding(
                    horizontal = 18.dp,
                    vertical = 10.dp
                ),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF7C3AED)
            )
        }
    }
}
