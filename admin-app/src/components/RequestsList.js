import React from 'react';
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
            id: 2,
            korisnik: "korisnik2",
            datumKreiranja: "12.12.2023 14:13:02"
        },
        {
            id: 3,
            korisnik: "korisnik3",
            datumKreiranja: "12.12.2023 14:13:03"
        },
        {
            id: 1,
            korisnik: "korisnik1",
            datumKreiranja: "12.12.2023 14:13:01"
        },
        {
            id: 2,
            korisnik: "korisnik2",
            datumKreiranja: "12.12.2023 14:13:02"
        },
        {
            id: 3,
            korisnik: "korisnik3",
            datumKreiranja: "12.12.2023 14:13:03"
        },
        {
            id: 1,
            korisnik: "korisnik1",
            datumKreiranja: "12.12.2023 14:13:01"
        },
        {
            id: 2,
            korisnik: "korisnik2",
            datumKreiranja: "12.12.2023 14:13:02"
        },
        {
            id: 3,
            korisnik: "korisnik3",
            datumKreiranja: "12.12.2023 14:13:03"
        },
        {
            id: 1,
            korisnik: "korisnik1",
            datumKreiranja: "12.12.2023 14:13:01"
        },
        {
            id: 2,
            korisnik: "korisnik2",
            datumKreiranja: "12.12.2023 14:13:02"
        },
        {
            id: 3,
            korisnik: "korisnik3",
            datumKreiranja: "12.12.2023 14:13:03"
        },
        {
            id: 1,
            korisnik: "korisnik1",
            datumKreiranja: "12.12.2023 14:13:01"
        },
        {
            id: 2,
            korisnik: "korisnik2",
            datumKreiranja: "12.12.2023 14:13:02"
        },
        {
            id: 3,
            korisnik: "korisnik3",
            datumKreiranja: "12.12.2023 14:13:03"
        },
        {
            id: 1,
            korisnik: "korisnik1",
            datumKreiranja: "12.12.2023 14:13:01"
        },
        {
            id: 2,
            korisnik: "korisnik2",
            datumKreiranja: "12.12.2023 14:13:02"
        },
        {
            id: 3,
            korisnik: "korisnik3",
            datumKreiranja: "12.12.2023 14:13:03"
        },
        {
            id: 1,
            korisnik: "korisnik1",
            datumKreiranja: "12.12.2023 14:13:01"
        },
        {
            id: 2,
            korisnik: "korisnik2",
            datumKreiranja: "12.12.2023 14:13:02"
        },
        {
            id: 3,
            korisnik: "korisnik3",
            datumKreiranja: "12.12.2023 14:13:03"
        },
        {
            id: 1,
            korisnik: "korisnik1",
            datumKreiranja: "12.12.2023 14:13:01"
        },
        {
            id: 2,
            korisnik: "korisnik2",
            datumKreiranja: "12.12.2023 14:13:02"
        },
        {
            id: 3,
            korisnik: "korisnik3",
            datumKreiranja: "12.12.2023 14:13:03"
        },
        {
            id: 1,
            korisnik: "korisnik1",
            datumKreiranja: "12.12.2023 14:13:01"
        },
        {
            id: 2,
            korisnik: "korisnik2",
            datumKreiranja: "12.12.2023 14:13:02"
        },
        {
            id: 3,
            korisnik: "korisnik3",
            datumKreiranja: "12.12.2023 14:13:03"
        },
        {
            id: 1,
            korisnik: "korisnik1",
            datumKreiranja: "12.12.2023 14:13:01"
        },
        {
            id: 2,
            korisnik: "korisnik2",
            datumKreiranja: "12.12.2023 14:13:02"
        },
        {
            id: 3,
            korisnik: "korisnik3",
            datumKreiranja: "12.12.2023 14:13:03"
        }
    ]

    return (
        <div className='user-list'>
            <h2 className='table-heading'>Zahtjevi za naloge</h2>
            <div className='table-wrapper'>
                <Table hover class="table table-borderless" className='my-table'>
                    <thead>
                        <tr>
                            <th className='table-header'></th>
                            <th className='table-header'>Korisnik</th>
                            <th className='table-header'>Vrijeme slanja zahtjeva</th>
                        </tr>
                    </thead>
                    <tbody>
                        {testArray.map(korisnik => <tr className='table-row'>
                            <th scope="row">{korisnik.id}</th>
                            <td>{korisnik.korisnik}</td>
                            <td>{korisnik.datumKreiranja}</td>
                        </tr>)}
                    </tbody>
                </Table>
            </div>
        </div>
    )
}

export default UserList;