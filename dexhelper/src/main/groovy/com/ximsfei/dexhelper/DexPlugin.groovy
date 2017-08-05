package com.ximsfei.dexhelper

import com.android.build.gradle.api.ApplicationVariant
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
                            def apkFile = new ZipFile(apkPath)
                            apkFile.entries().each { entry ->
                                if (entry.name.startsWith("classes")
                                        && entry.name.endsWith(".dex")) {
                                    Log.d("dex file", entry.name)
                                    DexParser dexParser = new DexParser(apkFile.getInputStream(entry))
                                    Log.d("method size", String.valueOf(dexParser.headerItem.methodIdsSize))
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
