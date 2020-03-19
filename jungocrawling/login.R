library(stringr)
options("scipen" = 100)
rD<-c()
remDr<-c()

  rD <-rsDriver(browser="fire",port=as.integer(i))
  remDr <- rD[["client"]]
  remDr$navigate("https://nid.naver.com/nidlogin.login?mode=form&url=https%3A%2F%2Fwww.naver.com")
  Sys.sleep(0.3)
  webElem <- remDr$findElement(using = 'css', "input[name='id']")
  writeClipboard("wjdrnjsgh159")
  webElem$sendKeysToElement(list(key = "control", "v"))
  writeClipboard("712812Zx!@")
  webElem <- remDr$findElement(using = 'css', "input[name='pw']")
  webElem$sendKeysToElement(list(key = "control", "v"))
  webElem <- remDr$findElement(using = 'css', "input[title='·Î±×ÀÎ']")
  webElem$clickElement()
  