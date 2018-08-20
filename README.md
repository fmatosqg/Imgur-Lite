# Instructions

## One time setup
- Copy file `secrets.gradle.sample` into `secrets.gradle`
- Replace placeholders with client ID and secret
- Open Android Studio

## Running
- Run with flavor ProdDebug to hit imgur server
- Run with flavor MockDebug or MockRelease to test UI with mocked data


# Questions
- why use 'DD/MM/YYYY h:mm a' when 'dd/MM/YYYY H:mm a' seems to work better? Pls next time specify examples not specs ;)
- images were cropped to avoid OOM crashes, there are workarounds but PO needs to confirm which point in the trade off is desired.