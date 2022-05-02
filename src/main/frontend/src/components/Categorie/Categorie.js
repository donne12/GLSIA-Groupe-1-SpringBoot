import React, {useEffect, useState} from "react";
import axios from "axios";
import authHeader from "../../constant/authHeader";
import { getAllCategorie } from "../../constant/api";

const Categorie = () => {
    const [categorie, setCategories] = useState([]);

    const getCategoie = () => {
        axios.get(getAllCategorie(), { headers: authHeader() })
    .then(response =>{ setCategories(response.data) ;});
    }

    useEffect(() => {
        getCategoie();
    },[])
    return(
<div className="container-fluid">
                    <h3 className="text-dark mb-4">Categorie</h3>
                    <div className="card shadow">
                        <div className="card-header py-3">
                            <p className="text-primary m-0 fw-bold">Categorie Info</p>
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
                                            <th>Designation</th>

                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        {categorie.map(item => (
                                        <tr>
                                            <td>{item?.id}</td>
                                            <td>{item?.designation}</td>
                                            
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
                                            <td><strong>Designation</strong></td>
                                            
                                            <td><strong>Action</strong></td>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                            
                        </div>
                    </div>
                
        </div>
    );
}

export default Categorie;