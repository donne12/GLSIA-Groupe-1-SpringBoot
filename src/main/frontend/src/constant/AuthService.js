import axios from "axios";
import {login, addUser} from "./api";
class AuthService {
  login(username, password) {
    var userIdentifier = new URLSearchParams();
    userIdentifier.append("username",username);
    userIdentifier.append("password",password);
    return axios
      .post(login(), userIdentifier)
      .then(response => {
        if (response.data.access_token) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }
        return response.data;
      });
  }
  logout() {
    localStorage.removeItem("user");
  }
  register(username, password) {
    return axios.post(addUser, {
      username,
      password
    });
  }
  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));;
  }
}
export default new AuthService();