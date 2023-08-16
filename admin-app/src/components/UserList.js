import React, {Component} from 'react';
import { Table } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.css'

const UserList = () => {

    const testArray = [
        {
            id: 1,
            korisnik: "korisnik1",
            datumKreiranja: "12.12.2023 14:13:01"
        },
        {
            id: 1,
            korisnik: "korisnik2",
            datumKreiranja: "12.12.2023 14:13:02"
        },
        {
            id: 1,
            korisnik: "korisnik3",
            datumKreiranja: "12.12.2023 14:13:03"
        }
    ]

        return( 
        <div>
            <h2>Lista</h2>
            <Table hover class="table table-borderless">
                <thead>
                    <tr>
                        <th></th>
                        <th>Korisnik</th>
                        <th>Vrijeme slanja zahtjeva</th>
                    </tr>
                </thead>
                <tbody>
                    {testArray.map(korisnik => <tr>
                        <td>{korisnik.id}</td>
                        <td>{korisnik.korisnik}</td>
                        <td>{korisnik.datumKreiranja}</td>
                    </tr>)}
                </tbody>
            </Table>
        </div>
        )
}

export default UserList;