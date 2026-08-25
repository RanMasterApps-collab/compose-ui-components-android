@Composable
fun FilterBottomSheetDemo() {

    var showFilterSheet by remember {
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
                showFilterSheet = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF5B5FEF)
            )
        ) {

            Icon(
                imageVector = Icons.Default.FilterList,
                contentDescription = null
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Text(
                text = "Open Filters",
                fontWeight = FontWeight.Bold
            )
        }
    }

    if (showFilterSheet) {

        FilterBottomSheet(
            onDismiss = {
                showFilterSheet = false
            },
            onApply = {
                showFilterSheet = false
            }
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    onDismiss: () -> Unit,
    onApply: () -> Unit
) {

    val SheetWhite = Color(0xFFFFFEFC)
    val TextDark = Color(0xFF172033)
    val TextGray = Color(0xFF6B7280)
    val Primary = Color(0xFF5B5FEF)
    val Border = Color(0xFFE5E7EB)

    var selectedCategory by remember {
        mutableStateOf("All")
    }

    var selectedSort by remember {
        mutableStateOf("Popular")
    }

    var selectedRating by remember {
        mutableStateOf(4f)
    }

    var priceRange by remember {
        mutableStateOf(20f..80f)
    }

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
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(
                    horizontal = 24.dp,
                    vertical = 8.dp
                )
        ) {

            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "Filter",
                        color = TextDark,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(3.dp)
                    )

                    Text(
                        text = "Customize your results",
                        color = TextGray,
                        fontSize = 14.sp
                    )
                }

                IconButton(
                    onClick = onDismiss
                ) {

                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = TextGray
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            // Canva illustration
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(
                        R.drawable.filter_bottom_sheet_illustration
                    ),
                    contentDescription = "Filter illustration",
                    modifier = Modifier.size(115.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // Category
            Text(
                text = "Category",
                color = TextDark,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            val categories = listOf(
                "All",
                "Popular",
                "Newest",
                "Recommended"
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(
                        rememberScrollState()
                    ),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                categories.forEach { category ->

                    FilterChip(
                        selected = selectedCategory == category,
                        onClick = {
                            selectedCategory = category
                        },
                        label = {
                            Text(
                                text = category,
                                fontWeight = FontWeight.SemiBold
                            )
                        },
                        leadingIcon = if (
                            selectedCategory == category
                        ) {
                            {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        } else {
                            null
                        },
                        shape = RoundedCornerShape(14.dp)
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            // Price
            Text(
                text = "Price Range",
                color = TextDark,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "$${priceRange.start.toInt()} - $${priceRange.endInclusive.toInt()}",
                color = Primary,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )

            RangeSlider(
                value = priceRange,
                onValueChange = {
                    priceRange = it
                },
                valueRange = 0f..100f,
                steps = 9,
                colors = SliderDefaults.colors(
                    thumbColor = Primary,
                    activeTrackColor = Primary,
                    inactiveTrackColor = Border
                )
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

                        // Rating
                        Text(
                            text = "Minimum Rating",
                            color = TextDark,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(4.dp)
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = Color(0xFFFFB300),
                                modifier = Modifier.size(20.dp)
                            )

                            Spacer(
                                modifier = Modifier.width(5.dp)
                            )

                            Text(
                                text = "${selectedRating.toInt()}+",
                                color = TextDark,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Slider(
                            value = selectedRating,
                            onValueChange = {
                                selectedRating = it
                            },
                            valueRange = 1f..5f,
                            steps = 3,
                            colors = SliderDefaults.colors(
                                thumbColor = Primary,
                                activeTrackColor = Primary,
                                inactiveTrackColor = Border
                            )
                        )

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        // Sort
                        Text(
                            text = "Sort By",
                            color = TextDark,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )

                        val sortOptions = listOf(
                            "Popular",
                            "Newest",
                            "Price: Low to High",
                            "Price: High to Low"
                        )

                        sortOptions.forEach { option ->

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(
                                        RoundedCornerShape(14.dp)
                                    )
                                    .clickable {
                                        selectedSort = option
                                    }
                                    .padding(
                                        vertical = 11.dp,
                                        horizontal = 4.dp
                                    ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                RadioButton(
                                    selected = selectedSort == option,
                                    onClick = {
                                        selectedSort = option
                                    },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = Primary
                                    )
                                )

                                Spacer(
                                    modifier = Modifier.width(6.dp)
                                )

                                Text(
                                    text = option,
                                    color = TextDark,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }

                        Spacer(
                            modifier = Modifier.height(18.dp)
                        )

                        // Bottom actions
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {

                            OutlinedButton(
                                onClick = {
                                    selectedCategory = "All"
                                    selectedSort = "Popular"
                                    selectedRating = 1f
                                    priceRange = 0f..100f
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(54.dp),
                                shape = RoundedCornerShape(17.dp)
                            ) {

                                Text(
                                    text = "Reset",
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Button(
                                onClick = onApply,
                                modifier = Modifier
                                    .weight(1.4f)
                                    .height(54.dp),
                                shape = RoundedCornerShape(17.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Primary
                                )
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null
                                )

                                Spacer(
                                    modifier = Modifier.width(7.dp)
                                )

                                Text(
                                    text = "Apply Filters",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

    }}
}
