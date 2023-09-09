import React, { useState, useEffect } from 'react';
import { Table } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.css';
import axios from 'axios';

const UserList = ({ switchTab, selectUser }) => {
    const [users, setUsers] = useState([]);
    const [search, setSearch] = useState('');

    useEffect(() => {
        fetchUsers();
    }, []);

    const fetchUsers = async () => {
        try {
            const response = await axios.get('http://localhost:8080/user/getAll');
            setUsers(response.data);
        } catch (error) {
            console.error('Error fetching users:', error);
        }
    };

    const handleRowClick = (id) => {
        console.log(id + " is clicked");
        switchTab("user");
    };

    const handleUserClick = (user) => {
        switchTab("user");
        selectUser(user);
    };

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
                <Table hover className="table table-bordered my-table">
                    <thead>
                        <tr>
                            <th className='table-header' scope="row"></th>
                            <th className='table-header'>Korisničko ime</th>
                            <th className='table-header'>Ime</th>
                            <th className='table-header'>Prezime</th>
                            {<th className='table-header'>Uloga</th>}
                        </tr>
                    </thead>
                    <tbody>
                        {users.filter((korisnik) => {
                            return search.toLowerCase() === '' ? korisnik : (
                                korisnik.korisnickoIme.toLowerCase().includes(search)
                                || korisnik.ime.toLowerCase().includes(search)
                                || korisnik.prezime.toLowerCase().includes(search)
                                || korisnik.uloga.toLowerCase().includes(search)
                            );
                        }).map(korisnik =>
                            <tr key={korisnik.idKorisnika} className='table-row' onClick={() => handleUserClick(korisnik)}>
                                <th>{korisnik.idKorisnika}</th>
                                <td>{korisnik.korisnickoIme}</td>
                                <td>{korisnik.ime}</td>
                                <td>{korisnik.prezime}</td>
                                {<td>{korisnik.uloga}</td>}
                            </tr>
                        )}
                    </tbody>
                </Table>
            </div>
        </div>
    );
};

export default UserList;
