@Composable
fun PremiumMusicCard() {

    var isPlaying by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1F2933)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            // Step 1: Album Artwork
            Image(
                painter = painterResource(R.drawable.music_img),
                contentDescription = "Album Artwork",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.FillBounds
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Step 2: Song Information
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "Midnight Dreams",
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "RAN Music • Chill Vibes",
                        fontSize = 12.sp,
                        color = Color(0xFFB8C0C8)
                    )
                }

                // Step 3: Play / Pause Button
                FilledIconButton(
                    onClick = {
                        isPlaying = !isPlaying
                    },
                    modifier = Modifier.size(48.dp),
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = Color(0xFF72C9C3),
                        contentColor = Color(0xFF1F2933)
                    )
                ) {
                    Icon(
                        imageVector = if (isPlaying) {
                            Icons.Default.Pause
                        } else {
                            Icons.Default.PlayArrow
                        },
                        contentDescription = if (isPlaying) {
                            "Pause"
                        } else {
                            "Play"
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Step 4: Music Progress
            LinearProgressIndicator(
                progress = {
                    if (isPlaying) 0.65f else 0.25f
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .clip(CircleShape),
                color = Color(0xFF72C9C3),
                trackColor = Color(0xFF3B4650)
            )

            Spacer(modifier = Modifier.height(7.dp))

            // Step 5: Time Information
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = if (isPlaying) "2:18" else "0:52",
                    fontSize = 11.sp,
                    color = Color(0xFFB8C0C8)
                )

                Text(
                    text = "3:42",
                    fontSize = 11.sp,
                    color = Color(0xFFB8C0C8)
                )
            }
        }
    }
}
