@Composable
fun PremiumOfferCard(
    onShopNow: () -> Unit = {}
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF7F4EF)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Step 1: Product Image + Discount Badge
            Box(
                modifier = Modifier
                    .size(145.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(R.drawable.card4),
                    contentDescription = "Sneaker",
                    modifier = Modifier.size(125.dp),
                    contentScale = ContentScale.Fit
                )

                // Step 2: Discount Badge
                Surface(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp),
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFF72C9C3)
                ) {
                    Text(
                        text = "30% OFF",
                        modifier = Modifier.padding(
                            horizontal = 8.dp,
                            vertical = 5.dp
                        ),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Step 3: Offer Details
            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "Urban Runner",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1F2933)
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "Limited time offer",
                    fontSize = 12.sp,
                    color = Color(0xFF7A8087)
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Step 4: Old + New Price
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "$120",
                        fontSize = 13.sp,
                        color = Color(0xFF9CA3AF),
                        textDecoration = TextDecoration.LineThrough
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "$84",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF72C9C3)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Step 5: Shop Now Button
                Button(
                    onClick = onShopNow,
                    modifier = Modifier
                        .height(42.dp),
                    shape = RoundedCornerShape(13.dp),
                    contentPadding = PaddingValues(
                        horizontal = 16.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1F2933)
                    )
                ) {
                    Text(
                        text = "Shop Now",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                        , color = Color.White
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(15.dp)

                    )
                }
            }
        }
    }
}
