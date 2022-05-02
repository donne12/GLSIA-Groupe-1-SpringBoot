import React from "react";
import AuthService from "../../constant/AuthService";
import UserService from "../../constant/UserService";
import ArticleService from "../../constant/ArticleService";


const User = () => {
    AuthService.login("Translucide",123456789);
    //console.log(UserService.getPublicContent());
    

    return(
        <div className="container-fluid">
            <h1>Getting Started</h1>
        </div>
    );
}

export default User;