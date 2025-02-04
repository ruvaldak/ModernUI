# Modern UI
[![CurseForge](http://cf.way2muchnoise.eu/full_352491_downloads.svg)](https://www.curseforge.com/minecraft/mc-mods/modern-ui)
[![CurseForge](http://cf.way2muchnoise.eu/versions/For%20Minecraft_352491_all.svg)](https://www.curseforge.com/minecraft/mc-mods/modern-ui)
[![MavenForge](https://img.shields.io/badge/dynamic/xml?color=yellowgreen&label=Latest%20Version&query=%2Fmetadata%2Fversioning%2Flatest&url=https%3A%2F%2Fmaven.izzel.io%2Freleases%2Ficyllis%2Fmodernui%2FModernUI-Forge%2Fmaven-metadata.xml)]()
[![MavenCore](https://img.shields.io/badge/dynamic/xml?color=orange&label=Core%20Version&query=%2Fmetadata%2Fversioning%2Flatest&url=https%3A%2F%2Fmaven.izzel.io%2Freleases%2Ficyllis%2Fmodernui%2FModernUI-Core%2Fmaven-metadata.xml)]()
[![Discord](https://img.shields.io/discord/696234198767501363?color=green&label=Discord&style=flat)](https://discord.gg/kmyGKt2)
### Homepage
Description and releases for Minecraft Mod are available on [CurseForge](https://www.curseforge.com/minecraft/mc-mods/modern-ui)  
If you have any questions, feel free to join our [Discord](https://discord.gg/kmyGKt2) server.
### License
* Modern UI
  - Copyright (C) 2019-2021 BloCamLimb. All rights reserved.
  - [![License](https://img.shields.io/badge/License-LGPL--3.0--or--later-blue.svg?style=flat-square)](https://www.gnu.org/licenses/lgpl-3.0.en.html)
  - Copyright (C) 2006 The Android Open Source Project
  - [![License](https://img.shields.io/badge/License-Apache%202.0-orange.svg?style=flat-square)](https://opensource.org/licenses/Apache-2.0)
* Textures, Shaders, Models, Documents, Translations
  - Copyright (C) 2019-2021 BloCamLimb et al.
  - [![License](https://img.shields.io/badge/License-CC%20BY--NC--SA%204.0-yellow.svg?style=flat-square)](https://creativecommons.org/licenses/by-nc-sa/4.0/)
* Sounds
  - [![License](https://img.shields.io/badge/License-No%20Restriction-green.svg?style=flat-square)](https://creativecommons.org/publicdomain/zero/1.0/)
* Third Party Libraries
  - [caffeine](https://github.com/ben-manes/caffeine) by Ben Manes
  - [flexmark-java](https://github.com/vsch/flexmark-java) by Atlassian Pty Ltd, Vladimir Schneider
  - [fastutil](https://github.com/vigna/fastutil) by Vigna
  - Apache Commons Lang 3
  - Apache Log4j 2
  - ICU4J by IBM
### Adding Modern UI to your project
#### Environment requirements
- Windows, Linux or Classic macOS
- JDK 11.0.8 or above
- OpenGL 4.5 or above
- (Optional) Forge 1.16.5-36.1.0
#### Gradle configuration
```
repositories {
    maven {
        name 'IzzelAliz Maven'
        url 'https://maven.izzel.io/releases'
    }
}
dependencies {
    implementation "icyllis.modernui:ModernUI-Core:${modernui_core_version}"
}
```
##### Forge Loom 0.7
```
dependencies {
    modCompile "icyllis.modernui:ModernUI-Forge:${modernui_version}"
}
```
##### ForgeGradle 4
Mixin 0.8.2 can't automatically remap the refmap
in forge dev environment, you may manually add mixin system properties and
re-run `genIntellijRuns`, see https://github.com/SpongePowered/Mixin/issues/462  
ForgeGradle 3 will ignore sources jar and dependencies, you may manually
add one that with `@pom` for dependencies, see https://github.com/MinecraftForge/ForgeGradle/issues/736  
These should be fixed in ForgeGradle 4 and Mixin 0.8.3.
```
minecraft {
    runs {
        client {
            property 'mixin.env.remapRefMap', 'true'
            property 'mixin.env.refMapRemappingFile', "${projectDir}/build/createSrgToMcp/output.srg"
        }
    }
}
dependencies {
    compile fg.deobf("icyllis.modernui:ModernUI-Forge:${modernui_version}")
    compile fg.deobf("icyllis.modernui:ModernUI-Forge:${modernui_version}@pom")
}
```
### Screenshots
![new](https://i.loli.net/2021/03/24/nMZhJaiz7qDp2xF.png)
#### out-of-date
![a](https://i.loli.net/2020/05/15/fYAow29d4JtqaGu.png)
![b](https://i.loli.net/2020/04/10/LDBFc1qo5wtnS8u.png)
