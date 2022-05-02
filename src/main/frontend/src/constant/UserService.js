import axios from 'axios';
import authHeader from './authHeader';
import {getAllUser,getSingleUser,addUser,deleteUser} from "./api";
class UserService {
  getPublicContent() {
    return axios.get(getAllUser(), { headers: authHeader() })
    .then(response =>{ return response.data;});
  }
  getOne(id) {
    return axios.get(getSingleUser(id), { headers: authHeader() })
    .then(response =>{ return response.data;});
  }
  addOne(username,password) {
      var user = {
          "username":username,
          "password":password
      }
    return axios.get(addUser(),user, { headers: authHeader() })
    .then(response =>{ return response.data;});
  }
  delete(id) {
    return axios.get(deleteUser(id), { headers: authHeader() })
    .then(response =>{ return response.data;});
  }
}
export default new UserService();