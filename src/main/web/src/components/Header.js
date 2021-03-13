import React from 'react';
import Button from './Button';


const Header = ({text, showAdd, onAdd, onEdit}) => {

    return (
        <header>
            <h1>{text}</h1>
            <Button text= {!showAdd ? 'Add' : 'Close'} onClick={onAdd} showAdd={showAdd}></Button>
        </header>
    )
}

export default Header
