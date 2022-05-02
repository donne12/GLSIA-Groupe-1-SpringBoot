import React, {useEffect, useState} from "react";
import axios from "axios";
import authHeader from "../../constant/authHeader";
import { getAllArticle } from "../../constant/api";

const Article = () => {
    const [article, setArticles] = useState([]);

    const getArticle = () => {
        axios.get(getAllArticle(), { headers: authHeader() })
    .then(response =>{ setArticles(response.data) ;});
    }

    useEffect(() => {
     getArticle();
    },[])

    console.log(article);
    
    return(
        <div className="container-fluid">
                    <h3 className="text-dark mb-4">Articles</h3>
                    <div className="card shadow">
                        <div className="card-header py-3">
                            <p className="text-primary m-0 fw-bold">Article Info</p>
                        </div>
                        <div className="card-body">
                            <div className="row">
                            <div className="col-md-6 text-nowrap">
                        <div id="dataTable_length" className="dataTables_length" aria-controls="dataTable">
                        <button type="button" class="btn btn-success">Add</button>
                        </div>
                    </div>
                                <div className="col-md-6">
                                    <div className="text-md-end dataTables_filter" id="dataTable_filter"><label className="form-label"><input type="search" className="form-control form-control-sm" aria-controls="dataTable" placeholder="Search"/></label></div>
                                </div>
                            </div>
                            <div className="table-responsive table mt-2" id="dataTable" role="grid" aria-describedby="dataTable_info">
                                <table className="table my-0" id="dataTable">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Nom</th>
                                            <th>QteStock</th>
                                            <th>QteSeuil</th>
                                            <th>Categorie</th>
                                            <th>Date</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        {article.map(item => (
                                        <tr>
                                            <td>{item?.id}</td>
                                            <td>{item?.libelle}</td>
                                            <td>{item?.qteStok}</td>
                                            <td>{item?.qteSeuil}</td>
                                            <td>{item?.categorie.designation}</td>
                                            <td>{item?.dateCreation}</td>
                                            <td><div class="btn-group" role="group" aria-label="Basic mixed styles example">
  
  <button type="button" class="btn btn-warning">edit</button>
  <button type="button" class="btn btn-success">approv</button>
  <button type="button" class="btn btn-danger">delete</button>
</div></td>
                                        </tr>
                                        ))}
                                                                        
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <td><strong>#</strong></td>
                                            <td><strong>Nom</strong></td>
                                            <td><strong>QteStock</strong></td>
                                            <td><strong>QteSeuil</strong></td>
                                            <td><strong>Categorie</strong></td>
                                            <td><strong>Date</strong></td>
                                            <td>Action</td>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                            
                        </div>
                    </div>
                
        </div>
        
    );
}

export default Article;