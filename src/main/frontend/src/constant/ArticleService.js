import axios from 'axios';
import authHeader from './authHeader';
import {getAllArticle,getSingleArticle,addArticle,deleteArticle} from "./api";
class ArticleService {
  getPublicContent() {
    return  axios.get(getAllArticle(), { headers: authHeader() })
    .then(response =>{ return response.data;});
   
  }
  getOne(id) {
    return axios.get(getSingleArticle(id), { headers: authHeader() })
    .then(response =>{ return response.data;});
  }
  addOne(libelle,seuil,prix,catId) {
    var article =
    {
        "libelle": libelle,
        "qteSeuil": seuil,
        "prix": prix,
        "categoryId": catId,
    }
    return axios.post(addArticle(), article , {headers: authHeader()  })
    .then(response =>{ return response.data;});
  }
  delete(id) {
    return axios.delete(deleteArticle(id), { headers: authHeader() })
    .then(response =>{ return response.data;});
  }
}
export default new ArticleService();