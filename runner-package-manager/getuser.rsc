import "System"

void getpass() {
  userpass = System.getProperty("user.pass")
  return userpass
}

void getname() {
  username = System.getProperty("user.name")
  return username
}
