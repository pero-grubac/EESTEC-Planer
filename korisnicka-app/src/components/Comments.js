import { Comment } from './Comment';

export const Comments = () => {

    const arrayEnd = 0;

    const comments = [
        {
            korisnik: {
                ime: "aaa",
                prezime: "Aaa",
            },
            datum: "123124798",
            tekst: "aaaaaaaaaaaaaaaaaaaa aaa aaaaaaaaaa aaaaa aaaaaaaaa"
        },
        {
            korisnik: {
                ime: "bbb",
                prezime: "Bbb",
            },
            datum: "123124798",
            tekst: "bbbbbbbbbb bbb bbbbbbb bbbb bbbbbbbb"
        },
        {
            korisnik: {
                ime: "ccc",
                prezime: "Ccc",
            },
            datum: "123124798",
            tekst: "cccccccc  ccccccccc ccc ccccccccccc cccccccc"
        },
    ]


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
            <textarea className='new-comment-input' placeholder='Novi komentar...'></textarea>
            <button>Dodaj komentar</button>
        </div>
    );
};