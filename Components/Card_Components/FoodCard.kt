@Composable
fun PremiumFoodCard() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Step 1: Food Image Container
            Box(
                modifier = Modifier
                    .size(118.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFFF7F4EF)),
                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(R.drawable.coffee_img),
                    contentDescription = "Food",
                    modifier = Modifier
                        .size(105.dp),
                    contentScale = ContentScale.Fit
                )

                // Step 2: Small Rating Badge
                Surface(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(6.dp),
                    shape = RoundedCornerShape(10.dp),
                    color = Color.White.copy(alpha = 0.95f),
                    shadowElevation = 3.dp
                ) {
                    Row(
                        modifier = Modifier.padding(
                            horizontal = 7.dp,
                            vertical = 4.dp
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier.size(13.dp),
                            tint = Color(0xFFF2C879)
                        )

                        Spacer(modifier = Modifier.width(3.dp))

                        Text(
                            text = "4.8",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1F2933)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(15.dp))

            // Step 3: Food Information
            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "Morning Ritual",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1F2933),
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "• Coffee  • Croissant ",
                    fontSize = 12.sp,
                    color = Color(0xFF7A7F87)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Step 4: Price + Add Button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "$12.90",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF72C9C3)
                    )

                    // Step 5: Compact Add Button
                    FilledIconButton(
                        onClick = { },
                        modifier = Modifier.size(40.dp),
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = Color(0xFF72C9C3),
                            contentColor = Color.White
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add to cart"
                        )
                    }
                }
            }
        }
    }
}
