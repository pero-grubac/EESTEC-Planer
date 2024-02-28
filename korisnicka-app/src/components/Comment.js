import {formatDateTime} from '../utils/formatDateTime';

export const Comment = ({author, commentText, dateTime}) => {

    return (
        <div className='comment-container'>
            <b className="comment-item comment-author">{author.ime + " " + author.prezime}</b>
            <p className="comment-item comment-text">{commentText}</p>
            <p className="comment-item comment-time">{formatDateTime(dateTime)}</p>
        </div>
    );
};