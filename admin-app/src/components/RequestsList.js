import React from 'react';
import { Table } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.css'

const UserList = ({ switchTab, selectRequest }) => {


    // testArray samo za svrhe testiranja, ovo treba fetchovati
    const testArray = [
        {
            id: 1,
            korisnickoIme: "korisnik1",
            ime: "ime1",
            prezime: "prezime1",
            email: "imeprezime@email.com",
            vrijemeKreiranja: "12.12.2023 14:13:01"
        },
        {
            id: 2,
            korisnickoIme: "korisnik2",
            ime: "ime1",
            prezime: "prezime1",
            email: "imeprezime@email.com",
            vrijemeKreiranja: "12.12.2023 14:13:02"
        },
        {
            id: 3,
            korisnickoIme: "korisnik3",
            ime: "ime1",
            prezime: "prezime1",
            email: "imeprezime@email.com",
            vrijemeKreiranja: "12.12.2023 14:13:03"
        },
        {
            id: 4,
            korisnickoIme: "korisnik1",
            ime: "ime1",
            prezime: "prezime1",
            email: "imeprezime@email.com",
            vrijemeKreiranja: "12.12.2023 14:13:01"
        },
        {
            id: 5,
            korisnickoIme: "korisnik2",
            ime: "ime1",
            prezime: "prezime1",
            email: "imeprezime@email.com",
            vrijemeKreiranja: "12.12.2023 14:13:02"
        },
        {
            id: 6,
            korisnickoIme: "korisnik3",
            ime: "ime1",
            prezime: "prezime1",
            email: "imeprezime@email.com",
            vrijemeKreiranja: "12.12.2023 14:13:03"
        },
        {
            id: 7,
            korisnickoIme: "korisnik1",
            ime: "ime1",
            prezime: "prezime1",
            email: "imeprezime@email.com",
            vrijemeKreiranja: "12.12.2023 14:13:01"
        },
        {
            id: 8,
            korisnickoIme: "korisnik2",
            ime: "ime1",
            prezime: "prezime1",
            email: "imeprezime@email.com",
            vrijemeKreiranja: "12.12.2023 14:13:02"
        },
        {
            id: 9,
            korisnickoIme: "korisnik3",
            ime: "ime1",
            prezime: "prezime1",
            email: "imeprezime@email.com",
            vrijemeKreiranja: "12.12.2023 14:13:03"
        },
        {
            id: 10,
            korisnickoIme: "korisnik1",
            ime: "ime1",
            prezime: "prezime1",
            email: "imeprezime@email.com",
            vrijemeKreiranja: "12.12.2023 14:13:01"
        },
        {
            id: 11,
            korisnickoIme: "korisnik2",
            ime: "ime1",
            prezime: "prezime1",
            email: "imeprezime@email.com",
            vrijemeKreiranja: "12.12.2023 14:13:02"
        },
        {
            id: 12,
            korisnickoIme: "korisnik3",
            ime: "ime1",
            prezime: "prezime1",
            email: "imeprezime@email.com",
            vrijemeKreiranja: "12.12.2023 14:13:03"
        },
        {
            id: 13,
            korisnickoIme: "korisnik1",
            ime: "ime1",
            prezime: "prezime1",
            email: "imeprezime@email.com",
            vrijemeKreiranja: "12.12.2023 14:13:01"
        },
        {
            id: 14,
            korisnickoIme: "korisnik2",
            ime: "ime1",
            prezime: "prezime1",
            email: "imeprezime@email.com",
            vrijemeKreiranja: "12.12.2023 14:13:02"
        },
        {
            id: 15,
            korisnickoIme: "korisnik3",
            ime: "ime1",
            prezime: "prezime1",
            email: "imeprezime@email.com",
            vrijemeKreiranja: "12.12.2023 14:13:03"
        },
        {
            id: 16,
            korisnickoIme: "korisnik1",
            ime: "ime1",
            prezime: "prezime1",
            email: "imeprezime@email.com",
            vrijemeKreiranja: "12.12.2023 14:13:01"
        },
        {
            id: 17,
            korisnickoIme: "korisnik2",
            ime: "ime1",
            prezime: "prezime1",
            email: "imeprezime@email.com",
            vrijemeKreiranja: "12.12.2023 14:13:02"
        },
        {
            id: 18,
            korisnickoIme: "korisnik3",
            ime: "ime1",
            prezime: "prezime1",
            email: "imeprezime@email.com",
            vrijemeKreiranja: "12.12.2023 14:13:03"
        },
        {
            id: 19,
            korisnickoIme: "korisnik1",
            ime: "ime1",
            prezime: "prezime1",
            email: "imeprezime@email.com",
            vrijemeKreiranja: "12.12.2023 14:13:01"
        },
        {
            id: 20,
            korisnickoIme: "korisnik2",
            ime: "ime1",
            prezime: "prezime1",
            email: "imeprezime@email.com",
            vrijemeKreiranja: "12.12.2023 14:13:02"
        },
        {
            id: 21,
            korisnickoIme: "korisnik3",
            ime: "ime1",
            prezime: "prezime1",
            email: "imeprezime@email.com",
            vrijemeKreiranja: "12.12.2023 14:13:03"
        },
        {
            id: 22,
            korisnickoIme: "korisnik1",
            ime: "ime1",
            prezime: "prezime1",
            email: "imeprezime@email.com",
            vrijemeKreiranja: "12.12.2023 14:13:01"
        },
        {
            id: 23,
            korisnickoIme: "korisnik2",
            ime: "ime1",
            prezime: "prezime1",
            email: "imeprezime@email.com",
            vrijemeKreiranja: "12.12.2023 14:13:02"
        },
        {
            id: 24,
            korisnickoIme: "korisnik3",
            ime: "ime1",
            prezime: "prezime1",
            email: "imeprezime@email.com",
            vrijemeKreiranja: "12.12.2023 14:13:03"
        },
        {
            id: 25,
            korisnickoIme: "korisnik1",
            ime: "ime1",
            prezime: "prezime1",
            email: "imeprezime@email.com",
            vrijemeKreiranja: "12.12.2023 14:13:01"
        },
        {
            id: 26,
            korisnickoIme: "korisnik2",
            ime: "ime1",
            prezime: "prezime1",
            email: "imeprezime@email.com",
            vrijemeKreiranja: "12.12.2023 14:13:02"
        },
        {
            id: 27,
            korisnickoIme: "korisnik3",
            ime: "ime1",
            prezime: "prezime1",
            email: "imeprezime@email.com",
            vrijemeKreiranja: "12.12.2023 14:13:03"
        }
    ]

    const handleRequestSelect = (request) => {
        switchTab("request");
        selectRequest(request);
    };

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
                        {testArray.map(korisnik => <tr key={korisnik.id} className='table-row' onClick={() => handleRequestSelect(korisnik)}>
                            <th scope="row">{korisnik.id}</th>
                            <td>{korisnik.korisnickoIme}</td>
                            <td>{korisnik.ime}</td>
                            <td>{korisnik.prezime}</td>
                            <td>{korisnik.vrijemeKreiranja}</td>
                        </tr>)}
                    </tbody>
                </Table>
            </div>
        </div>
    )
}

export default UserList;