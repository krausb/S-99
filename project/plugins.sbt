addSbtPlugin("org.scoverage"        % "sbt-scoverage"          % "1.5.1")
addSbtPlugin("com.typesafe.sbt"     % "sbt-git"                % "0.9.3")
addSbtPlugin("com.eed3si9n"         % "sbt-assembly"           % "0.14.6")
addSbtPlugin("org.scalastyle"       %% "scalastyle-sbt-plugin" % "1.0.0")
addSbtPlugin("org.wartremover"      % "sbt-wartremover"        % "2.2.1")
addSbtPlugin("com.typesafe.sbt"     % "sbt-native-packager"    % "1.3.2")
addSbtPlugin("de.heikoseeberger"    % "sbt-header"             % "4.1.0")
addSbtPlugin("com.github.gseitz"    % "sbt-release"            % "1.0.7")
addSbtPlugin("com.typesafe.sbt"     % "sbt-license-report"     % "1.2.0")
addSbtPlugin("io.gatling"           % "gatling-sbt"            % "2.2.2")
addSbtPlugin("com.lucidchart"       % "sbt-scalafmt"           % "1.15")

libraryDependencies += "org.slf4j" % "slf4j-nop" % "1.7.25" // Needed by sbt-git
