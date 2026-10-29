
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val AppBg = Color(0xFF090E1C)
private val Panel = Color(0xFF151F36)
private val TextMuted = Color(0xFF9AA8C2)
private val Violet = Color(0xFF8957F5)
private val Magenta = Color(0xFFE84CA8)
private val Aqua = Color(0xFF35D9E8)
private val Mint = Color(0xFF50DDB1)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme(
                    primary = Violet, secondary = Magenta, background = AppBg,
                    surface = Panel, onBackground = Color.White, onSurface = Color.White
                )
            ) {
                Surface(Modifier.fillMaxSize(), color = AppBg) {
                    AdvancedAnimationLab()
                }
            }
        }
    }
}

@Composable
fun AdvancedAnimationLab() {
    LazyColumn(
        modifier = Modifier.fillMaxSize().background(
            Brush.verticalGradient(listOf(Color(0xFF17102F), AppBg, Color(0xFF101C32)))
        ),
        contentPadding = PaddingValues(start = 18.dp, end = 18.dp, top = 26.dp, bottom = 34.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        item { Header() }
        item { DemoCard("01", "Shimmer Loading Effect", "Skeleton UI while content loads", Icons.Default.Bolt, Aqua) { ShimmerDemo() } }
        item { DemoCard("02", "Infinite Animation", "A smooth, repeating motion", Icons.Default.Star, Magenta) { InfiniteDemo() } }
        item { DemoCard("03", "AnimatedContent", "Animate between changing content", Icons.Default.ShoppingBag, Violet) { AnimatedContentDemo() } }
        item { DemoCard("04", "Crossfade Animation", "Fade between two UI states", Icons.Default.Favorite, Magenta) { CrossfadeDemo() } }
        item { DemoCard("05", "Animated Tab Indicator", "A sliding indicator for tabs", Icons.Default.Home, Mint) { AnimatedTabDemo() } }
        item { Footer() }
    }
}

@Composable
private fun Header() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(48.dp).clip(RoundedCornerShape(15.dp)).background(Brush.linearGradient(listOf(Violet, Magenta))), contentAlignment = Alignment.Center) {
                Icon(Icons.Default.Bolt, contentDescription = null, tint = Color.White, modifier = Modifier.size(28.dp))
            }
            Spacer(Modifier.width(12.dp))
            Column {
                Text("ANIMATION LAB", color = Aqua, fontSize = 12.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = 2.sp)
                Text("Advanced UI Playground", color = Color.White, fontSize = 21.sp, fontWeight = FontWeight.Bold)
            }
        }
        Text("Loading. Motion. Magic.", color = Color.White, fontSize = 32.sp, lineHeight = 38.sp, fontWeight = FontWeight.ExtraBold)
        Text("5 advanced UI animations built with Kotlin and Jetpack Compose.", color = TextMuted, fontSize = 14.sp, lineHeight = 21.sp)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Chip("5 Animations"); Chip("Material 3"); Chip("Interactive")
        }
    }
}

@Composable
private fun Chip(label: String) {
    Box(Modifier.clip(CircleShape).background(Color.White.copy(alpha = 0.07f)).padding(horizontal = 11.dp, vertical = 8.dp)) {
        Text(label, color = Color(0xFFD8DFF0), fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun DemoCard(number: String, title: String, subtitle: String, icon: ImageVector, accent: Color, content: @Composable () -> Unit) {
    Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(25.dp), colors = CardDefaults.cardColors(containerColor = Panel.copy(alpha = 0.97f)), elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)) {
        Column(Modifier.padding(17.dp), verticalArrangement = Arrangement.spacedBy(17.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(47.dp).clip(RoundedCornerShape(14.dp)).background(accent.copy(alpha = 0.16f)), contentAlignment = Alignment.Center) {
                    Text(number, color = accent, fontSize = 17.sp, fontWeight = FontWeight.ExtraBold)
                }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text(title, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(4.dp))
                    Text(subtitle, color = TextMuted, fontSize = 11.sp)
                }
                Icon(icon, contentDescription = null, tint = accent, modifier = Modifier.size(21.dp))
            }
            content()
        }
    }
}

@Composable
private fun PrimaryButton(label: String, onClick: () -> Unit) {
    Box(Modifier.fillMaxWidth().clip(RoundedCornerShape(15.dp)).background(Brush.horizontalGradient(listOf(Violet, Magenta))).clickable(onClick = onClick).padding(vertical = 14.dp), contentAlignment = Alignment.Center) {
        Text(label, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun ShimmerDemo() {
    var showLoading by remember { mutableStateOf(true) }
    val transition = androidx.compose.animation.core.rememberInfiniteTransition(label = "shimmerTransition")
    val shimmerPosition by transition.animateFloat(-1f, 2f, androidx.compose.animation.core.infiniteRepeatable(androidx.compose.animation.core.tween(1400)), label = "shimmerPosition")
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        if (showLoading) {
            val shimmerBrush = Brush.linearGradient(
                colors = listOf(Color(0xFF202B45), Color(0xFF53627F), Color(0xFF202B45)),
                start = androidx.compose.ui.geometry.Offset(shimmerPosition * 500f, 0f),
                end = androidx.compose.ui.geometry.Offset(shimmerPosition * 500f + 300f, 150f)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(58.dp).clip(RoundedCornerShape(16.dp)).background(shimmerBrush))
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(9.dp)) {
                    Box(Modifier.fillMaxWidth(0.72f).height(13.dp).clip(CircleShape).background(shimmerBrush))
                    Box(Modifier.fillMaxWidth(0.96f).height(10.dp).clip(CircleShape).background(shimmerBrush))
                    Box(Modifier.fillMaxWidth(0.55f).height(10.dp).clip(CircleShape).background(shimmerBrush))
                }
            }
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(58.dp).clip(RoundedCornerShape(16.dp)).background(Brush.linearGradient(listOf(Violet, Magenta))), contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Cloud, contentDescription = null, tint = Color.White, modifier = Modifier.size(29.dp))
                }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text("Your content is ready", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text("Loaded successfully with a smooth transition.", color = TextMuted, fontSize = 11.sp)
                }
                Icon(Icons.Default.CheckCircle, contentDescription = "Loaded", tint = Mint)
            }
        }
        PrimaryButton(if (showLoading) "Show Loaded Content" else "Replay Shimmer") { showLoading = !showLoading }
    }
}

@Composable
private fun InfiniteDemo() {
    val transition = rememberInfiniteTransition(label = "infiniteDemo")
    val rotation by transition.animateFloat(0f, 360f, infiniteRepeatable(tween(2200)), label = "infiniteRotation")
    val pulse by transition.animateFloat(0.82f, 1.08f, infiniteRepeatable(tween(900), repeatMode = androidx.compose.animation.core.RepeatMode.Reverse), label = "infinitePulse")
    Box(Modifier.fillMaxWidth().height(145.dp), contentAlignment = Alignment.Center) {
        Box(Modifier.size(105.dp).scale(pulse).background(Brush.linearGradient(listOf(Violet, Magenta)), CircleShape), contentAlignment = Alignment.Center) {
            Icon(Icons.Default.Star, contentDescription = "Continuously rotating star", tint = Color.White, modifier = Modifier.size(47.dp).rotate(rotation))
        }
    }
    Text("Repeating animation • no button needed", color = Magenta, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
}

@Composable
private fun AnimatedContentDemo() {
    var page by remember { mutableIntStateOf(0) }
    val titles = listOf("Your Orders", "Your Wishlist", "Your Profile")
    val descriptions = listOf("Track recent purchases", "Save items for later", "Manage your account")
    val icons = listOf(Icons.Default.ShoppingBag, Icons.Default.Favorite, Icons.Default.Person)
    Column(verticalArrangement = Arrangement.spacedBy(13.dp)) {
        Box(Modifier.fillMaxWidth().height(125.dp).clip(RoundedCornerShape(20.dp)).background(Color(0xFF202B49)), contentAlignment = Alignment.Center) {
            AnimatedContent(
                targetState = page,
                transitionSpec = {
                    (fadeIn(tween(300)) + slideInVertically(tween(300)) { it / 4 })
                        .togetherWith(fadeOut(tween(180)))
                        .using(SizeTransform(clip = false))
                },
                label = "dashboardContent"
            ) { target ->
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(icons[target], contentDescription = null, tint = Aqua, modifier = Modifier.size(31.dp))
                    Text(titles[target], color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(descriptions[target], color = TextMuted, fontSize = 12.sp)
                }
            }
        }
        PrimaryButton("Next Content") { page = (page + 1) % titles.size }
    }
}

@Composable
private fun CrossfadeDemo() {
    var selected by remember { mutableStateOf(false) }
    Crossfade(targetState = selected, animationSpec = tween(500), label = "crossfadeDemo") { isPremium ->
        Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp)).background(Brush.linearGradient(if (isPremium) listOf(Color(0xFF7B2FF7), Color(0xFFF107A3)) else listOf(Color(0xFF1E8FA5), Color(0xFF2855C5))).padding(18.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(56.dp).clip(RoundedCornerShape(17.dp)).background(Color.White.copy(alpha = 0.18f)), contentAlignment = Alignment.Center) {
                Icon(if (isPremium) Icons.Default.Star else Icons.Default.Cloud, contentDescription = null, tint = Color.White, modifier = Modifier.size(31.dp))
            }
            Spacer(Modifier.width(13.dp))
            Column(Modifier.weight(1f)) {
                Text(if (isPremium) "Premium Member" else "Free Account", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
                Spacer(Modifier.height(4.dp))
                Text(if (isPremium) "All features unlocked" else "Explore the essentials", color = Color.White.copy(alpha = 0.82f), fontSize = 12.sp)
            }
        }
    }
    Spacer(Modifier.height(12.dp))
    PrimaryButton(if (selected) "Switch to Free" else "Preview Premium") { selected = !selected }
}

@Composable
private fun AnimatedTabDemo() {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Home", "Explore", "Profile")
    val icons = listOf(Icons.Default.Home, Icons.Default.Star, Icons.Default.Person)
    val indicatorOffset by animateFloatAsState(selectedTab.toFloat(), animationSpec = tween(350, easing = FastOutSlowInEasing), label = "tabIndicator")
    Column(verticalArrangement = Arrangement.spacedBy(13.dp)) {
        Box {
            Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(Color(0xFF0D1528)).padding(5.dp)) {
                tabs.forEachIndexed { index, label ->
                    val selected = selectedTab == index
                    Column(Modifier.weight(1f).clip(RoundedCornerShape(12.dp)).clickable { selectedTab = index }.padding(vertical = 11.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        Icon(icons[index], contentDescription = null, tint = if (selected) Color.White else TextMuted, modifier = Modifier.size(20.dp))
                        Text(label, color = if (selected) Color.White else TextMuted, fontSize = 11.sp, fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal)
                    }
                }
            }
            BoxWithConstraints(Modifier.fillMaxWidth().padding(horizontal = 5.dp)) {
                val tabWidth = maxWidth / 3
                Box(Modifier.offset(x = tabWidth * indicatorOffset, y = 0.dp).width(tabWidth).height(3.dp).clip(CircleShape).background(Brush.horizontalGradient(listOf(Aqua, Mint))))
            }
        }
        Text(when (selectedTab) { 0 -> "Welcome home — your dashboard is ready."; 1 -> "Explore new ideas and discover more."; else -> "Your profile and preferences." }, color = TextMuted, fontSize = 12.sp)
    }
}

@Composable
private fun Footer() {
    Column(Modifier.fillMaxWidth().padding(top = 5.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(7.dp)) {
        Text("POWERED BY JETPACK COMPOSE", color = Aqua, fontSize = 11.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = 1.8.sp)
        Text("Design • Animate • Deliver", color = TextMuted, fontSize = 13.sp)
    }
}
