import React from 'react';
import { Table } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.css'

const UserList = ({requestClicked}) => {

    const testArray = [
        {
            id: 1,
            korisnik: "korisnik1",
            ime: "ime1",
            prezime: "prezime1",
            datumKreiranja: "12.12.2023 14:13:01"
        },
        {
            id: 2,
            korisnik: "korisnik2",
            ime: "ime1",
            prezime: "prezime1",
            datumKreiranja: "12.12.2023 14:13:02"
        },
        {
            id: 3,
            korisnik: "korisnik3",
            ime: "ime1",
            prezime: "prezime1",
            datumKreiranja: "12.12.2023 14:13:03"
        },
        {
            id: 4,
            korisnik: "korisnik1",
            ime: "ime1",
            prezime: "prezime1",
            datumKreiranja: "12.12.2023 14:13:01"
        },
        {
            id: 5,
            korisnik: "korisnik2",
            ime: "ime1",
            prezime: "prezime1",
            datumKreiranja: "12.12.2023 14:13:02"
        },
        {
            id: 6,
            korisnik: "korisnik3",
            ime: "ime1",
            prezime: "prezime1",
            datumKreiranja: "12.12.2023 14:13:03"
        },
        {
            id: 7,
            korisnik: "korisnik1",
            ime: "ime1",
            prezime: "prezime1",
            datumKreiranja: "12.12.2023 14:13:01"
        },
        {
            id: 8,
            korisnik: "korisnik2",
            ime: "ime1",
            prezime: "prezime1",
            datumKreiranja: "12.12.2023 14:13:02"
        },
        {
            id: 9,
            korisnik: "korisnik3",
            ime: "ime1",
            prezime: "prezime1",
            datumKreiranja: "12.12.2023 14:13:03"
        },
        {
            id: 10,
            korisnik: "korisnik1",
            ime: "ime1",
            prezime: "prezime1",
            datumKreiranja: "12.12.2023 14:13:01"
        },
        {
            id: 11,
            korisnik: "korisnik2",
            ime: "ime1",
            prezime: "prezime1",
            datumKreiranja: "12.12.2023 14:13:02"
        },
        {
            id: 12,
            korisnik: "korisnik3",
            ime: "ime1",
            prezime: "prezime1",
            datumKreiranja: "12.12.2023 14:13:03"
        },
        {
            id: 13,
            korisnik: "korisnik1",
            ime: "ime1",
            prezime: "prezime1",
            datumKreiranja: "12.12.2023 14:13:01"
        },
        {
            id: 14,
            korisnik: "korisnik2",
            ime: "ime1",
            prezime: "prezime1",
            datumKreiranja: "12.12.2023 14:13:02"
        },
        {
            id: 15,
            korisnik: "korisnik3",
            ime: "ime1",
            prezime: "prezime1",
            datumKreiranja: "12.12.2023 14:13:03"
        },
        {
            id: 16,
            korisnik: "korisnik1",
            ime: "ime1",
            prezime: "prezime1",
            datumKreiranja: "12.12.2023 14:13:01"
        },
        {
            id: 17,
            korisnik: "korisnik2",
            ime: "ime1",
            prezime: "prezime1",
            datumKreiranja: "12.12.2023 14:13:02"
        },
        {
            id: 18,
            korisnik: "korisnik3",
            ime: "ime1",
            prezime: "prezime1",
            datumKreiranja: "12.12.2023 14:13:03"
        },
        {
            id: 19,
            korisnik: "korisnik1",
            ime: "ime1",
            prezime: "prezime1",
            datumKreiranja: "12.12.2023 14:13:01"
        },
        {
            id: 20,
            korisnik: "korisnik2",
            ime: "ime1",
            prezime: "prezime1",
            datumKreiranja: "12.12.2023 14:13:02"
        },
        {
            id: 21,
            korisnik: "korisnik3",
            ime: "ime1",
            prezime: "prezime1",
            datumKreiranja: "12.12.2023 14:13:03"
        },
        {
            id: 22,
            korisnik: "korisnik1",
            ime: "ime1",
            prezime: "prezime1",
            datumKreiranja: "12.12.2023 14:13:01"
        },
        {
            id: 23,
            korisnik: "korisnik2",
            ime: "ime1",
            prezime: "prezime1",
            datumKreiranja: "12.12.2023 14:13:02"
        },
        {
            id: 24,
            korisnik: "korisnik3",
            ime: "ime1",
            prezime: "prezime1",
            datumKreiranja: "12.12.2023 14:13:03"
        },
        {
            id: 25,
            korisnik: "korisnik1",
            ime: "ime1",
            prezime: "prezime1",
            datumKreiranja: "12.12.2023 14:13:01"
        },
        {
            id: 26,
            korisnik: "korisnik2",
            ime: "ime1",
            prezime: "prezime1",
            datumKreiranja: "12.12.2023 14:13:02"
        },
        {
            id: 27,
            korisnik: "korisnik3",
            ime: "ime1",
            prezime: "prezime1",
            datumKreiranja: "12.12.2023 14:13:03"
        }
    ]

    return (
        <div className='user-list'>
            <h2 className='table-heading'>Zahtjevi za naloge</h2>
            <div className='table-wrapper'>
                <Table hover className="table table-bordered my-table">
                    <thead>
                        <tr>
                            <th className='table-header'></th>
                            <th className='table-header'>Korisničko ime</th>
                            <th className='table-header'>Ime</th>
                            <th className='table-header'>Prezime</th>
                            <th className='table-header'>Vrijeme slanja zahtjeva</th>
                        </tr>
                    </thead>
                    <tbody>
                        {testArray.map(korisnik => <tr key={korisnik.id} className='table-row' onClick={() => requestClicked("request")}>
                            <th scope="row">{korisnik.id}</th>
                            <td>{korisnik.korisnik}</td>
                            <td>{korisnik.ime}</td>
                            <td>{korisnik.prezime}</td>
                            <td>{korisnik.datumKreiranja}</td>
                        </tr>)}
                    </tbody>
                </Table>
            </div>
        </div>
    )
}

export default UserList;