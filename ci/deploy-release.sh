#!/bin/bash
set -e
set -uxo pipefail

# Decrypt and import signing key
openssl aes-256-cbc -K $encrypted_acb7ac75d6748eb4_key -iv $encrypted_acb7ac75d6748eb4_iv -in ci/whitesky.asc.enc -out ci/whitesky.asc -d
gpg --armor --import ci/whitesky.asc

./mvnw -B deploy --settings 'ci/settings.xml' -DperformRelease=true -Dmaven.test.skip=true
