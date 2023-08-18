import React from 'react';
import { Table } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.css'

const UserList = () => {

    const testArray = [
        {
            id: 1,
            korisnickoIme: "korisnik1",
            ime: "ime1",
            prezime: "prezime1",
            uloga: "koordinator"
        },
        {
            id: 2,
            korisnickoIme: "korisnik2",
            ime: "ime2",
            prezime: "prezime2",
            uloga: "koordinator"
        },
        {
            id: 3,
            korisnickoIme: "korisnik3",
            ime: "ime3",
            prezime: "prezime3",
            uloga: "koordinator"
        }
    ]

        return( 
        <div className='user-list'>
            <h2>Korisnici</h2>
            <Table hover class="table table-borderless" className='my-table'>
                <thead>
                    <tr>
                        <th scope="row"></th>
                        <th>Ime</th>
                        <th>Prezime</th>
                        <th>Uloga</th>
                    </tr>
                </thead>
                <tbody>
                    {testArray.map(korisnik => <tr className='table-row'>
                        <th>{korisnik.id}</th>
                        <td>{korisnik.ime}</td>
                        <td>{korisnik.prezime}</td>
                        <td>{korisnik.uloga}</td>
                    </tr>)}
                </tbody>
            </Table>
        </div>
        )
}

export default UserList;