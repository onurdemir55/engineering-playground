dependencies {
    implementation("io.github.onurdemir55:resurrections-rss:2.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
}

tasks.test {
    useJUnitPlatform()
}