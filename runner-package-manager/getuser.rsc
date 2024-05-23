import "System"

void getpass() {
  UserPass = System.getProperty("user.pass")
  return UserPass
}

void getuser() {
  UserName = System.getProperty("user.name")
  return UserName
}
