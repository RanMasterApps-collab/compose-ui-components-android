import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
// ---------------------------------------------------------
// TIMELINE DATA
// ---------------------------------------------------------

data class TimelineItem(
    val title: String,
    val description: String,
    val date: String,
    val icon: ImageVector
)

@Composable
fun PremiumTimelineProgressDemo() {

    // Step 1: Define timeline items
    val timelineItems = listOf(
        TimelineItem(
            title = "Order Placed",
            description = "Your order has been successfully placed.",
            date = "10:20 AM",
            icon = Icons.Default.ShoppingBag
        ),
        TimelineItem(
            title = "Order Confirmed",
            description = "The seller has confirmed your order.",
            date = "10:45 AM",
            icon = Icons.Default.Check
        ),
        TimelineItem(
            title = "Shipped",
            description = "Your package is on its way.",
            date = "12:30 PM",
            icon = Icons.Default.LocalShipping
        ),
        TimelineItem(
            title = "Out for Delivery",
            description = "Your package is near your location.",
            date = "02:15 PM",
            icon = Icons.Default.LocationOn
        ),
        TimelineItem(
            title = "Delivered",
            description = "Your order has been delivered successfully.",
            date = "03:10 PM",
            icon = Icons.Default.Home
        )
    )

    // Step 2: Track the current timeline position
    var currentStep by remember {
        mutableIntStateOf(2)
    }

    // Step 3: Animate the timeline progress
    val animatedProgress by animateFloatAsState(
        targetValue = currentStep.toFloat() /
                (timelineItems.size - 1),
        animationSpec = tween(
            durationMillis = 700,
            easing = FastOutSlowInEasing
        ),
        label = "timelineProgress"
    )

    val primaryColor = Color(0xFF6C63FF)
    val backgroundColor = Color(0xFFF5F6FA)
    val darkText = Color(0xFF171A2B)
    val secondaryText = Color(0xFF8A8FA3)
    val trackColor = Color(0xFFE5E6EF)
    val successColor = Color(0xFF22B573)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {

            // -------------------------------------------------
            // HEADER
            // -------------------------------------------------

            Text(
                text = "Order Timeline",
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
                color = darkText
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Track your order progress",
                fontSize = 14.sp,
                color = secondaryText
            )

            Spacer(modifier = Modifier.height(17.dp))

            // -------------------------------------------------
            // PROGRESS SUMMARY
            // -------------------------------------------------

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFEAE8FF)),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector = Icons.Default.LocalShipping,
                            contentDescription = null,
                            tint = primaryColor,
                            modifier = Modifier.size(26.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = "Delivery Progress",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = darkText
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "${currentStep + 1} of ${timelineItems.size} steps completed",
                            fontSize = 12.sp,
                            color = secondaryText
                        )
                    }

                    Text(
                        text = "${(animatedProgress * 100).toInt()}%",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = primaryColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // -------------------------------------------------
            // TIMELINE
            // -------------------------------------------------

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {

                // Background timeline line
                Box(
                    modifier = Modifier
                        .padding(start = 25.dp)
                        .width(4.dp)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(50))
                        .background(trackColor)
                )

                // Animated completed timeline line
                Box(
                    modifier = Modifier
                        .padding(start = 25.dp)
                        .width(4.dp)
                        .fillMaxHeight(animatedProgress)
                        .clip(RoundedCornerShape(50))
                        .background(primaryColor)
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    timelineItems.forEachIndexed { index, item ->

                        val completed = index <= currentStep
                        val active = index == currentStep

                        // -----------------------------------------
                        // TIMELINE ITEM
                        // -----------------------------------------

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.Top
                        ) {

                            // Timeline icon
                            Box(
                                modifier = Modifier
                                    .size(54.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (completed)
                                            primaryColor
                                        else
                                            Color.White
                                    ),
                                contentAlignment = Alignment.Center
                            ) {

                                Icon(
                                    imageVector = if (completed)
                                        Icons.Default.Check
                                    else
                                        item.icon,
                                    contentDescription = item.title,
                                    tint = if (completed)
                                        Color.White
                                    else
                                        secondaryText,
                                    modifier = Modifier.size(22.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(14.dp))

                            // Timeline content card
                            Card(
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(20.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = if (active)
                                        Color(0xFFF0EEFF)
                                    else
                                        Color.White
                                ),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = if (active) 5.dp else 2.dp
                                )
                            ) {

                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(15.dp)
                                ) {

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {

                                        Text(
                                            text = item.title,
                                            modifier = Modifier.weight(1f),
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = darkText
                                        )

                                        // Status badge
                                        Surface(
                                            shape = RoundedCornerShape(50),
                                            color = if (completed)
                                                Color(0xFFE4F8EF)
                                            else
                                                Color(0xFFF0F1F5)
                                        ) {

                                            Text(
                                                text = if (completed)
                                                    "Done"
                                                else
                                                    "Pending",
                                                modifier = Modifier.padding(
                                                    horizontal = 10.dp,
                                                    vertical = 5.dp
                                                ),
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = if (completed)
                                                    successColor
                                                else
                                                    secondaryText
                                            )
                                        }
                                    }

                                    Spacer(
                                        modifier = Modifier.height(7.dp)
                                    )

                                    Text(
                                        text = item.description,
                                        fontSize = 12.sp,
                                        color = secondaryText,
                                        lineHeight = 18.sp
                                    )

                                    Spacer(
                                        modifier = Modifier.height(8.dp)
                                    )

                                    Text(
                                        text = item.date,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = primaryColor
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // -------------------------------------------------
            // NEXT STEP BUTTON
            // -------------------------------------------------

            Button(
                onClick = {
                    if (currentStep < timelineItems.lastIndex) {
                        currentStep++
                    }
                },
                enabled = currentStep < timelineItems.lastIndex,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryColor
                )
            ) {

                Text(
                    text = if (currentStep ==
                        timelineItems.lastIndex
                    ) {
                        "Completed"
                    } else {
                        "Next Step"
                    }
                )
            }
        }
    }
}
