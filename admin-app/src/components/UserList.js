import React, { useState } from 'react';
import { Table } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.css'

const UserList = ({userClicked}) => {

    const testArray = [
        {
            id: 1,
            korisnickoIme: "korisnik1",
            ime: "ime1",
            prezime: "prezime1",
            uloga: "clan upravnog odbora"
        },
        {
            id: 2,
            korisnickoIme: "korisnik2",
            ime: "ime2",
            prezime: "prezime2",
            uloga: "korisnik"
        },
        {
            id: 3,
            korisnickoIme: "korisnik3",
            ime: "ime3",
            prezime: "prezime3",
            uloga: "koordinator"
        },
        {
            id: 4,
            korisnickoIme: "korisnik1",
            ime: "ime1",
            prezime: "prezime1",
            uloga: "koordinator"
        },
        {
            id: 5,
            korisnickoIme: "korisnik2",
            ime: "ime2",
            prezime: "prezime2",
            uloga: "koordinator"
        },
        {
            id: 6,
            korisnickoIme: "korisnik3",
            ime: "ime3",
            prezime: "prezime3",
            uloga: "koordinator"
        },
        {
            id: 7,
            korisnickoIme: "korisnik1",
            ime: "ime1",
            prezime: "prezime1",
            uloga: "koordinator"
        },
        {
            id: 8,
            korisnickoIme: "korisnik2",
            ime: "ime2",
            prezime: "prezime2",
            uloga: "koordinator"
        },
        {
            id: 9,
            korisnickoIme: "korisnik3",
            ime: "ime3",
            prezime: "prezime3",
            uloga: "koordinator"
        },
        {
            id: 10,
            korisnickoIme: "korisnik1",
            ime: "ime1",
            prezime: "prezime1",
            uloga: "koordinator"
        },
        {
            id: 11,
            korisnickoIme: "korisnik2",
            ime: "ime2",
            prezime: "prezime2",
            uloga: "koordinator"
        },
        {
            id: 12,
            korisnickoIme: "korisnik3",
            ime: "ime3",
            prezime: "prezime3",
            uloga: "koordinator"
        },
        {
            id: 13,
            korisnickoIme: "korisnik1",
            ime: "ime1",
            prezime: "prezime1",
            uloga: "koordinator"
        },
        {
            id: 14,
            korisnickoIme: "korisnik2",
            ime: "ime2",
            prezime: "prezime2",
            uloga: "koordinator"
        },
        {
            id: 15,
            korisnickoIme: "korisnik3",
            ime: "ime3",
            prezime: "prezime3",
            uloga: "koordinator"
        },
        {
            id: 16,
            korisnickoIme: "korisnik1",
            ime: "ime1",
            prezime: "prezime1",
            uloga: "koordinator"
        },
        {
            id: 17,
            korisnickoIme: "korisnik2",
            ime: "ime2",
            prezime: "prezime2",
            uloga: "koordinator"
        },
        {
            id: 18,
            korisnickoIme: "korisnik3",
            ime: "ime3",
            prezime: "prezime3",
            uloga: "koordinator"
        },
        {
            id: 19,
            korisnickoIme: "korisnik1",
            ime: "ime1",
            prezime: "prezime1",
            uloga: "koordinator"
        },
        {
            id: 20,
            korisnickoIme: "korisnik2",
            ime: "ime2",
            prezime: "prezime2",
            uloga: "koordinator"
        }
    ]

    const [search, setSearch] = useState('');

    const handleRowClick = (id) => {
        console.log(id + " is clicked").
        userClicked("user");
    }

    return (
        <div className='user-list'>
            <div className='search'>
                <input
                    onChange={(e) => setSearch(e.target.value)}
                    placeholder='Pretraga'>
                </input>
            </div>

            <h2>Korisnici</h2>
            <div className=' table-wrapper table-wrapper-users'>
                <Table hover class="table table-borderless" className='my-table'>
                    <thead>
                        <tr>
                            <th className='table-header' scope="row"></th>
                            <th className='table-header'>Korisničko ime</th>
                            <th className='table-header'>Ime</th>
                            <th className='table-header'>Prezime</th>
                            <th className='table-header'>Uloga</th>
                        </tr>
                    </thead>
                    <tbody>
                        {testArray.filter((korisnik) => {
                            return search.toLowerCase() === '' ? korisnik : (
                                korisnik.korisnickoIme.toLowerCase().includes(search)
                                || korisnik.ime.toLowerCase().includes(search)
                                || korisnik.prezime.toLowerCase().includes(search)
                                || korisnik.uloga.toLowerCase().includes(search)
                            )
                        }).map(korisnik => <tr key={korisnik.id} className='table-row' onClick={() => userClicked("user")}>
                            <th>{korisnik.id}</th>
                            <td>{korisnik.korisnickoIme}</td>
                            <td>{korisnik.ime}</td>
                            <td>{korisnik.prezime}</td>
                            <td>{korisnik.uloga}</td>
                        </tr>)}
                    </tbody>
                </Table>
            </div>

        </div>
    )
}

export default UserList;