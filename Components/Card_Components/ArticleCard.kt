@Composable
fun PremiumArticleCard(
    onClick: () -> Unit = {}
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp)
  ,      shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 7.dp
        )
    ) {

        Column {

            // Step 1: Article Image + Category Badge
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
            ) {

                Image(
                    painter = painterResource(R.drawable.laptop_img),
                    contentDescription = "Technology Article",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(
                            RoundedCornerShape(
                                topStart = 26.dp,
                                topEnd = 26.dp
                            )
                        ),
                    contentScale = ContentScale.Crop
                )

                // Step 2: Category Badge
                Surface(
                    modifier = Modifier
                        .padding(14.dp)
                        .align(Alignment.TopStart),
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFF72C9C3)
                ) {
                    Text(
                        text = "TECHNOLOGY",
                        modifier = Modifier.padding(
                            horizontal = 11.dp,
                            vertical = 6.dp
                        ),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        letterSpacing = 0.6.sp
                    )
                }

                // Step 3: Bookmark Button
                Surface(
                    modifier = Modifier
                        .padding(14.dp)
                        .size(40.dp)
                        .align(Alignment.TopEnd),
                    shape = CircleShape,
                    color = Color.White.copy(alpha = 0.94f),
                    shadowElevation = 4.dp
                ) {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            imageVector = Icons.Default.BookmarkBorder,
                            contentDescription = "Bookmark",
                            tint = Color(0xFF1F2933)
                        )
                    }
                }
            }

            // Step 4: Article Content
            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    text = "5 Android UI Trends You Should Know",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 26.sp,
                    color = Color(0xFF1F2933),
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Explore modern UI ideas that can make your Android apps feel cleaner and more engaging.",
                    fontSize = 13.sp,
                    lineHeight = 19.sp,
                    color = Color(0xFF747A82),
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Step 5: Article Metadata
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.PersonOutline,
                        contentDescription = null,
                        modifier = Modifier.size(17.dp),
                        tint = Color(0xFF72C9C3)
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = "RAN Master Apps",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF4B5563)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "•",
                        color = Color(0xFFB0B5BA)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "5 min read",
                        fontSize = 12.sp,
                        color = Color(0xFF7A8087)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Step 6: Read More
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Read Article",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF72C9C3)
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(17.dp),
                        tint = Color(0xFF72C9C3)
                    )
                }
            }
        }
    }
}
