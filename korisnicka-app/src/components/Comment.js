import {formatDateTime} from '../utils/formatDateTime';

export const Comment = ({author, commentText, dateTime}) => {

    return (
        <div>
            <b className="comment-author">{author.ime + " " + author.prezime}</b>
            <p className="comment-text">{commentText}</p>
            <p className="comment-time">{formatDateTime(dateTime)}</p>
        </div>
    );
};