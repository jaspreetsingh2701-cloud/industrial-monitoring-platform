# Accept the operation we want to perform.
param(
    [ValidateSet("compile", "run", "test", "package", "clean")]
    [string]$Task = "run"
)

# Resolve the repository root, regardless of the terminal's current directory.
$RepoRoot = Split-Path -Parent $PSScriptRoot

# Select the shared Maven parent and Device Service module.
$MavenArgs = @("-f", "services/pom.xml", "-pl", "device-service")

# Execute the requested Maven operation.
Push-Location $RepoRoot

try {
    switch ($Task) {
        "compile" {
            & mvn @MavenArgs clean compile
        }
        "run" {
            & mvn @MavenArgs org.springframework.boot:spring-boot-maven-plugin:4.1.2:run
        }
        "test" {
            & mvn @MavenArgs clean test
        }
        "package" {
            & mvn @MavenArgs clean package
        }
        "clean" {
            & mvn @MavenArgs clean
        }
    }

    # Return an error if Maven fails.
    if ($LASTEXITCODE -ne 0) {
        throw "Maven command failed with exit code $LASTEXITCODE"
    }
}
finally {
    # Restore the terminal's original directory.
    Pop-Location
}
