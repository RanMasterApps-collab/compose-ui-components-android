@Composable
fun ProfileBottomSheetDemo() {

    var showProfileSheet by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(
            onClick = {
                showProfileSheet = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF635BFF)
            )
        ) {

            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Text(
                text = "Open Profile",
                fontWeight = FontWeight.Bold
            )
        }
    }

    if (showProfileSheet) {

        ProfileBottomSheet(
            onDismiss = {
                showProfileSheet = false
            },
            onEditProfile = {
                showProfileSheet = false
            },
            onSettings = {
                showProfileSheet = false
            },
            onLogout = {
                showProfileSheet = false
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileBottomSheet(
    onDismiss: () -> Unit,
    onEditProfile: () -> Unit,
    onSettings: () -> Unit,
    onLogout: () -> Unit
) {

    val SheetWhite = Color(0xFFFFFEFC)
    val TextDark = Color(0xFF172033)
    val TextGray = Color(0xFF6B7280)
    val Primary = Color(0xFF635BFF)
    val LightPurple = Color(0xFFF1EFFF)
    val Danger = Color(0xFFEF4444)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = SheetWhite,
        shape = RoundedCornerShape(
            topStart = 30.dp,
            topEnd = 30.dp
        ),
        dragHandle = {

            Box(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .width(42.dp)
                    .height(5.dp)
                    .clip(
                        RoundedCornerShape(50)
                    )
                    .background(
                        Color(0xFFD1D5DB)
                    )
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 24.dp,
                    vertical = 8.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Canva Illustration
            Image(
                painter = painterResource(
                    R.drawable.profile_illustration
                ),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(120.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Your Profile",
                color = TextDark,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "Manage your account and personal preferences.",
                color = TextGray,
                fontSize = 14.sp,
                lineHeight = 21.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(
                    horizontal = 12.dp
                )
            )

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            // Profile Card
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                color = LightPurple
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(58.dp)
                            .clip(CircleShape)
                            .background(Primary),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }

                    Spacer(
                        modifier = Modifier.width(14.dp)
                    )

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = "Alex Johnson",
                            color = TextDark,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(3.dp)
                        )

                        Text(
                            text = "alex@example.com",
                            color = TextGray,
                            fontSize = 13.sp
                        )
                    }

                    Icon(
                        imageVector = Icons.Default.Verified,
                        contentDescription = "Verified",
                        tint = Color(0xFF22C55E),
                        modifier = Modifier.size(22.dp)
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            // Edit Profile
            ProfileActionItem(
                icon = Icons.Default.Edit,
                title = "Edit Profile",
                subtitle = "Update your personal information",
                iconColor = Primary,
                onClick = onEditProfile
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            // Settings
            ProfileActionItem(
                icon = Icons.Default.Settings,
                title = "Account Settings",
                subtitle = "Manage preferences and privacy",
                iconColor = Color(0xFF8B5CF6),
                onClick = onSettings
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            // Logout
            ProfileActionItem(
                icon = Icons.Default.Logout,
                title = "Log Out",
                subtitle = "Sign out from this account",
                iconColor = Danger,
                onClick = onLogout,
                showArrow = false
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )
        }
    }
}

@Composable
fun ProfileActionItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    iconColor: Color,
    onClick: () -> Unit,
    showArrow: Boolean = true
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(18.dp)
            )
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 6.dp,
                vertical = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(
                    RoundedCornerShape(15.dp)
                )
                .background(
                    iconColor.copy(alpha = 0.10f)
                ),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = iconColor,
                modifier = Modifier.size(22.dp)
            )
        }

        Spacer(
            modifier = Modifier.width(14.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = title,
                color = Color(0xFF172033),
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(
                modifier = Modifier.height(2.dp)
            )

            Text(
                text = subtitle,
                color = Color(0xFF6B7280),
                fontSize = 12.sp
            )
        }

        if (showArrow) {

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color(0xFF9CA3AF),
                modifier = Modifier.size(22.dp)
            )
        }
    }
}
