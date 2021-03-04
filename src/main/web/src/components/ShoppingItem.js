import React, {useState}from 'react';
import {TiDelete} from 'react-icons/ti'
import {MdEdit} from 'react-icons/md'

const ShoppingItem = ({item, matched, matchClicked, onEdit, onDelete, onMatchItem, onCancelMatch}) => {

    const [clicked, setClicked] = useState(false)

    const handleClick = () => {        
        if(!clicked) {
        onMatchItem(item.name)
        // if(matched.length === 0) {
        //     // alert("There is no campaigns yet, please add them first.")
        //     return
        // }
        setClicked(true)
        }else{
        onCancelMatch()
        setClicked(false)
        }
    }

    const handleBlur = () => {
        onCancelMatch()
        setClicked(false)
    }
    
    return (
        <div className="shopping-item" tabIndex="-1" onClick={handleClick} onBlur={handleBlur}>
            <span>{item.name} </span>  
            <span>   
                <MdEdit color="grey" size="2rem"  onClick={() => onEdit(item.id)}/>
                <TiDelete color="red" size="2rem" onClick={() => onDelete(item.id)}/>
            </span>
        </div>
    )
}

export default ShoppingItem
