import React, { FormEventHandler, useEffect, useState } from 'react';
import { getResourcesList, createResource } from './Resources.api';
import { Resource } from './types';
import { Box, IconButton, makeStyles, TableBody, TableCell, TableContainer, TableHead, TableRow, TextField, Typography } from '@material-ui/core/';
import {Delete} from '@material-ui/icons'
import { Button, Table } from 'react-bootstrap';

// interface ResourcesProps {
//   Resource: any

// }

// const Resources: React.FC<ResourcesProps> = ({Resource}) => {

// }
//const [game, setGame] = useState<Resource | null>(null)


const useStyles = makeStyles({
  tableRow: {
    cursor: 'pointer'
  },
  textField: {
    marginBottom: '16px',
    marginRight: '16px'
  }
})

const onFormSubmit: FormEventHandler<HTMLFormElement> = async (e) => {
  e.preventDefault()
  console.log('on submit')
  //const newResource = await createResource(game)
  
}



function Resources() {
  const classes = useStyles();
  const [resources, setResources] = useState<Resource[] | null>(null)
  const [stateResourceName, setResourceName] = useState("");
  const [stateResourceLink, setResourceLink] = useState("");

  const onFormValueChangeResourceName = (e: React.ChangeEvent<HTMLInputElement>) => {
    console.log(e.target.value)
    console.log(e.target.name)
    setResourceName(e.target.value)
  }
  const onFormValueChangeResourceLink = (e: React.ChangeEvent<HTMLInputElement>) => {
    console.log(e.target.value)
    console.log(e.target.name)
    setResourceLink(e.target.value)
  }
  
  useEffect(() => {
    const fetchResourceList = async () => {
      const resourceList = await getResourcesList()
      console.log(resourceList)
      setResources(resourceList)
    }
    fetchResourceList()
  },[])

  
  return (<>
  <Typography variant="h3">Resources</Typography>
  <TableContainer>
  <Table>
    <TableHead>
      <TableRow>
        <TableCell>ID</TableCell>
        <TableCell>Resource Name</TableCell>
        <TableCell>Resource Link</TableCell>
        <TableCell>Actions</TableCell>
      </TableRow>
    </TableHead>
    <TableBody>
      {resources && resources.map(resource => (
        <TableRow className={classes.tableRow} hover key={resource.resourceId}>
          <TableCell>
            {resource.resourceId}
          </TableCell>
          <TableCell>
            {resource.resourceName}
          </TableCell>
          <TableCell>
            {resource.resourceLink}
          </TableCell>
          <TableCell>
            <IconButton>
              <Delete />
            </IconButton>
          </TableCell>
        </TableRow>
      ))}
    </TableBody>
  </Table>
  </TableContainer>
  <Typography variant="h3">Add Resources</Typography>
  <form onSubmit={onFormSubmit}> 
    <Box display="flex" alignItems="center" justifyContent="center" flexDirection="column">
      <TextField onChange={onFormValueChangeResourceName} required className={classes.textField} name="ResourceName"  value={stateResourceName} label="Resource Name" placeholder="ResourceName" />
      <TextField onChange={onFormValueChangeResourceLink} required className={classes.textField} name="ResourceLink"  value={stateResourceLink} label="Resource Link" placeholder="ResourceLink"/>
      <Button type="submit" variant="contained" color="primary">Submit</Button>
    </Box>
  </form>
  </>  
  );
}

export default Resources;