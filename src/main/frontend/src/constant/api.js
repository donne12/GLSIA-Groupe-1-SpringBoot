export const api = "http://localhost:8080";

// CATEGORIE
export const getAllCategorie = () => {
    return api+"/categorie/all";
};

export const getSingleCategorie = (id) => {
  return api+`/categorie/find/${id}`;
};

export const deleteCategorie = (id) => {
  return api+`/categorie/delete/${id}`;
};

export const addCategorie = () => {
  return api+"/categorie/add";
};

// ARTICLE
export const getAllArticle = () => {
  return api+"/article/all";
};

export const getSingleArticle = (id) => {
return api+`/article/find/${id}`;
};

export const deleteArticle = (id) => {
return api+`/article/delete/${id}`;
};

export const addArticle = () => {
return api+"/article/add";
};

// APPROVISIONNEMENT
export const getAllApprov = () => {
  return api+"/approv/all";
};

export const getSingleApprov = (id) => {
return api+`/approv/find/${id}`;
};

export const deleteApprov = (id) => {
return api+`/approv/delete/${id}`;
};

export const addApprov = () => {
return api+"/approv/add";
};

// VENTE
export const getAllVente = () => {
  return api+"/vente/all";
};

export const getSingleVente = (id) => {
return api+`/vente/find/${id}`;
};

export const deleteVente = (id) => {
return api+`/vente/delete/${id}`;
};

export const addVente = () => {
return api+"/vente/add";
};

// LIGNEVENTE
export const getAllLigneVente = () => {
  return api+"/ligneVvente/all";
};

export const getSingleLigneVente = (id) => {
return api+`/ligneVente/find/${id}`;
};

export const deleteLigneVente = (id) => {
return api+`/ligneVente/delete/${id}`;
};

export const addLigneVente = () => {
return api+"/ligneVente/add";
};

// USER
export const getAllUser = () => {
  return api+"/user/all";
};

export const getSingleUser = (id) => {
return api+`/user/find/${id}`;
};

export const deleteUser = (id) => {
return api+`/user/delete/${id}`;
};

export const addUser = () => {
return api+"/user/add";
};

//authentication
export const login = () => {
return api+"/login";
};
  
export const logout = () => {
return api+"/logout";
};





