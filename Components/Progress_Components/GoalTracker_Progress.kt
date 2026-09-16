import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun PremiumGoalTrackerProgressDemo() {

    // ---------------------------------------------------------
    // STEP 1: Create goal data
    // ---------------------------------------------------------

    val totalTasks = 8

    var completedTasks by remember {
        mutableIntStateOf(5)
    }

    var isUpdating by remember {
        mutableStateOf(false)
    }

    // ---------------------------------------------------------
    // STEP 2: Calculate progress
    // ---------------------------------------------------------

    val targetProgress =
        completedTasks.toFloat() / totalTasks.toFloat()

    // ---------------------------------------------------------
    // STEP 3: Animate progress smoothly
    // ---------------------------------------------------------

    val animatedProgress by animateFloatAsState(
        targetValue = targetProgress,
        animationSpec = tween(
            durationMillis = 700,
            easing = FastOutSlowInEasing
        ),
        label = "goalProgress"
    )

    val primaryColor = Color(0xFF6C63FF)
    val backgroundColor = Color(0xFFF5F6FA)
    val darkText = Color(0xFF171A2B)
    val secondaryText = Color(0xFF8A8FA3)
    val trackColor = Color(0xFFE8E7F2)
    val successColor = Color(0xFF22B573)
    val orangeColor = Color(0xFFFF9F43)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // -------------------------------------------------
            // STEP 4: Build header
            // -------------------------------------------------

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "Goal Tracker",
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Bold,
                        color = darkText
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Stay focused and reach your goal",
                        fontSize = 13.sp,
                        color = secondaryText
                    )
                }

                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFFF0DD)),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Default.EmojiEvents,
                        contentDescription = null,
                        tint = orangeColor,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            // -------------------------------------------------
            // STEP 5: Create main progress card
            // -------------------------------------------------

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 7.dp
                )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Today's Goal",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = secondaryText
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // -------------------------------------------------
                    // STEP 6: Add circular goal progress
                    // -------------------------------------------------

                    Box(
                        modifier = Modifier.size(190.dp),
                        contentAlignment = Alignment.Center
                    ) {

                        CircularProgressIndicator(
                            progress = { animatedProgress },
                            modifier = Modifier.fillMaxSize(),
                            color = primaryColor,
                            trackColor = trackColor,
                            strokeWidth = 16.dp,
                            strokeCap = StrokeCap.Round
                        )

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = "${(animatedProgress * 100).toInt()}%",
                                fontSize = 38.sp,
                                fontWeight = FontWeight.Bold,
                                color = darkText
                            )

                            Text(
                                text = "Completed",
                                fontSize = 12.sp,
                                color = secondaryText
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "$completedTasks of $totalTasks tasks completed",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = darkText
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = if (completedTasks == totalTasks)
                            "Amazing! Goal completed 🎉"
                        else
                            "${totalTasks - completedTasks} tasks remaining",
                        fontSize = 12.sp,
                        color = if (completedTasks == totalTasks)
                            successColor
                        else
                            secondaryText
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // -------------------------------------------------
            // STEP 7: Add statistics cards
            // -------------------------------------------------

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                GoalStatCard(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.TaskAlt,
                    value = "$completedTasks",
                    label = "Completed",
                    iconBackground = Color(0xFFE9E7FF),
                    iconColor = primaryColor
                )

                GoalStatCard(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.LocalFireDepartment,
                    value = "7",
                    label = "Day Streak",
                    iconBackground = Color(0xFFFFEBDD),
                    iconColor = orangeColor
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // -------------------------------------------------
            // STEP 8: Add goal status card
            // -------------------------------------------------

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF0EEFF)
                )
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(primaryColor),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector = Icons.Default.Flag,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(22.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(13.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = "Daily Target",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = darkText
                        )

                        Spacer(modifier = Modifier.height(3.dp))

                        Text(
                            text = "Keep going — you're making progress!",
                            fontSize = 11.sp,
                            color = secondaryText
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // -------------------------------------------------
            // STEP 9: Add update goal button
            // -------------------------------------------------

            Button(
                onClick = {

                    if (!isUpdating && completedTasks < totalTasks) {

                        isUpdating = true

                        completedTasks++
                        isUpdating = false
                    }

                },
                enabled = !isUpdating &&
                        completedTasks < totalTasks,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryColor
                )
            ) {

                Icon(
                    imageVector = if (completedTasks == totalTasks)
                        Icons.Default.Check
                    else
                        Icons.Default.TaskAlt,
                    contentDescription = null,
                    modifier = Modifier.size(19.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = if (completedTasks == totalTasks)
                        "Goal Completed"
                    else
                        "Complete Next Task",
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}


// -------------------------------------------------------------
// PREMIUM STAT CARD
// -------------------------------------------------------------

@Composable
fun GoalStatCard(
    modifier: Modifier = Modifier,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    value: String,
    label: String,
    iconBackground: Color,
    iconColor: Color
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(iconBackground),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(21.dp)
                )
            }

            Spacer(modifier = Modifier.height(9.dp))

            Text(
                text = value,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF171A2B)
            )

            Text(
                text = label,
                fontSize = 11.sp,
                color = Color(0xFF8A8FA3)
            )
        }
    }
}
