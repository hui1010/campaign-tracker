import React from 'react'

const Button = ({text, showAdd, onClick}) => {
    return (
        <button className={`btn btn-${!showAdd?'add':'close'}`} onClick={onClick}>
            {text}
        </button>
    )
}

export default Button
