package com.ximsfei.dexhelper

import com.android.build.gradle.api.ApplicationVariant
import com.ximsfei.dexhelper.utils.FileUtils
import com.ximsfei.dexhelper.utils.Log
import org.gradle.api.Plugin
import org.gradle.api.Project

import java.util.zip.ZipFile

/**
 * Created by ximsfei on 2017/7/15.
 */
class DexPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.android.applicationVariants.all { ApplicationVariant variant ->
            project.getTasksByName("assemble" + variant.name.capitalize(), false).each { task ->
                task.doLast {
                    variant.outputs.each { output ->
                        if (output.outputFile.absolutePath.endsWith(".apk")) {
                            def apkPath = output.outputFile.absolutePath
                            Log.d("apkPath", apkPath)
                            Log.d("apkFolder", output.splitFolder.absolutePath)
                            def apkFile = new ZipFile(apkPath)
                            def dexFolder = new File(output.splitFolder.getParent(), "dex")
                            Log.d("dexFolder", dexFolder.absolutePath)
                            apkFile.entries().each { entry ->
                                if (entry.name.startsWith("classes")
                                        && entry.name.endsWith(".dex")) {
                                    Log.d("dex", entry.name)
                                    def dexFile = new File(dexFolder, entry.name)
                                    FileUtils.copyToFile(apkFile.getInputStream(entry), dexFile)
                                    DexParser.parse(dexFile)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
