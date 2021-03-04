import React, {useState} from 'react';
import ShoppingItem from './ShoppingItem';
import Button from './Button';

const ShoppingList = ({list, matched, onEdit, onDelete, onMatchAll, onMatchItem, onCancelMatch}) => {

    const names = []
    list.map(item => names.push(item.name))

    const [buttonClicked, setButtonClicked] = useState(false)
    const handleClick = () => {
        
        if(!buttonClicked){
            onMatchAll(names)
            setButtonClicked(true)
        }else {
            onCancelMatch()
            setButtonClicked(false)
        }
    }
    
    return (
        <div className="shopping-list-list">
            <span>Try clicking on the item, or </span>
            <button className="btn btn-match" onClick={handleClick}>{buttonClicked ? 'Unmatch all' : 'Match all'}</button>
            <ul className="shopping-list-items">
                {list.map((item) => (<li key={item.id}><ShoppingItem item={item} matched={matched} matchClicked={buttonClicked} onEdit={onEdit} onDelete={onDelete} onMatchItem={onMatchItem} onCancelMatch={onCancelMatch}/></li>))}
            </ul>
        
        </div>
    )
}

export default ShoppingList
