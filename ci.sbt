inThisBuild(
  List(
    homepage                            := Some(url("https://github.com/nafg/cloud-logging-logback-scala")),
    licenses                            := List("Apache-2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0")),
    developers                          := List(
      Developer("nafg", "Naftoli Gugenheim", "98384+nafg@users.noreply.github.com", url("https://github.com/nafg"))
    ),
    dynverGitDescribeOutput             :=
      dynverGitDescribeOutput.value.map { o =>
        o.copy(dirtySuffix = o.dirtySuffix.value match {
          case "" => o.dirtySuffix
          case _  => sbtdynver.GitDirtySuffix("_")
        })
      },
    dynverSonatypeSnapshots             := true,
    githubWorkflowJavaVersions          := Seq(JavaSpec.zulu("11")),
    githubWorkflowScalaVersions         := githubWorkflowScalaVersions.value.map(_.replaceFirst("\\d+$", "x")),
    githubWorkflowTargetTags ++= Seq("v*"),
    githubWorkflowPublishTargetBranches := Seq(RefPredicate.StartsWith(Ref.Tag("v"))),
    githubWorkflowPublish               := Seq(
      WorkflowStep.Sbt(
        List("ci-release"),
        env = Map(
          "PGP_PASSPHRASE"    -> "${{ secrets.PGP_PASSPHRASE }}",
          "PGP_SECRET"        -> "${{ secrets.PGP_SECRET }}",
          "SONATYPE_PASSWORD" -> "${{ secrets.SONATYPE_PASSWORD }}",
          "SONATYPE_USERNAME" -> "${{ secrets.SONATYPE_USERNAME }}"
        )
      )
    )
  )
)
