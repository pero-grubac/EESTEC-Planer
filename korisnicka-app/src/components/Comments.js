import { useState, useEffect } from "react";
import { Comment } from './Comment';
import axios from "axios";
import { useNavigate } from "react-router-dom";

export const Comments = ({ loggedUser, task }) => {

    const [comments, setComments] = useState([]);
    const [newCommentText, setNewCommentText] = useState("");

    const navigate = useNavigate();

    useEffect(() => {
        fetchLogs(task);
    }, [task]);

    const fetchLogs = async (task) => {
        try {

            const response = await axios.get(`http://localhost:8080/comments/${task.idZadatak}`, {
                headers: {
                    "Content-Type": "application/json",
                    Authorization: "Bearer " + localStorage.getItem("token"),
                },
            });

            if (response.status === 403) {
                localStorage.clear();
                navigate("/", { replace: true });
            }

            let counter = 1;
            setComments((response.data).map(comment => ({ ...comment, idTable: counter++ })));

        } catch (error) {
            console.error("Error fetching requests:", error);
        }
    };


    // const comments = [
    //     {
    //         korisnik: {
    //             ime: "aaa",
    //             prezime: "Aaa",
    //         },
    //         datum: "123124798",
    //         tekst: "aaaaaaaaaaaaaaaaaaaa aaa aaaaaaaaaa aaaaa aaaaaaaaa"
    //     },
    //     {
    //         korisnik: {
    //             ime: "bbb",
    //             prezime: "Bbb",
    //         },
    //         datum: "123124798",
    //         tekst: "bbbbbbbbbb bbb bbbbbbb bbbb bbbbbbbb"
    //     },
    //     {
    //         korisnik: {
    //             ime: "ccc",
    //             prezime: "Ccc",
    //         },
    //         datum: "123124798",
    //         tekst: "cccccccc  ccccccccc ccc ccccccccccc cccccccc"
    //     },
    // ]


    const handleSubmitNewComment = async (e) => {
        e.preventDefault();
        try {
            const createComment = await axios.post(
                "http://localhost:8080/comments",
                {
                    "idZadatak": task.idZadatak,
                    "korisnik": loggedUser,
                    "tekst": newCommentText
                },
                {
                    headers: {
                        "Content-Type": "application/json",
                        Authorization: "Bearer " + localStorage.getItem("token"),
                    },
                }
            );

            if (createComment.status === 403) {
                localStorage.clear();
                navigate("/", { replace: true });
            }

            fetchLogs(task);
            setNewCommentText("");

        } catch (error) {
            console.error(error);
        }
    }

    return (
        <div className='comments-container'>
            <div className='comments-list-container'>
                {
                    comments.map(comment => (
                        <Comment author={comment.korisnik} commentText={comment.tekst} dateTime={comment.datum}>
                        </Comment>
                    ))
                }
            </div>
            <textarea
                className='new-comment-input'
                placeholder='Novi komentar...'
                value={newCommentText}
                onChange={(e) => setNewCommentText(e.target.value)}
            ></textarea>
            <button onClick={handleSubmitNewComment}>Dodaj komentar</button>
        </div>
    );
};