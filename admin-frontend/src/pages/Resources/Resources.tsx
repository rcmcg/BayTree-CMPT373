import React, { FormEventHandler, useEffect, useState } from 'react';
import { getResourcesList } from './Resources.api';
import { Resource } from './types';
import { Box, IconButton, makeStyles, TableBody, TableCell, TableContainer, TableHead, TableRow, TextField, Typography } from '@material-ui/core/';
import {Delete} from '@material-ui/icons'
import { Button, Table } from 'react-bootstrap';

// interface ResourcesProps {
//   Resource: any

// }

// const Resources: React.FC<ResourcesProps> = ({Resource}) => {

// }

const useStyles = makeStyles({
  tableRow: {
    cursor: 'pointer'
  },
  textField: {
    marginBottom: '16px',
    marginRight: '16px'
  }
})

const onFormSubmit: FormEventHandler<HTMLFormElement> = (e) => {
  e.preventDefault()
  console.log('on submit')
}

function Resources() {
  const classes = useStyles();
  const [resources, setResource] = useState<Resource[] | null>(null)
  useEffect(() => {
    const fetchResourceList = async () => {
      const resourceList = await getResourcesList()
      console.log(resourceList)
      setResource(resourceList)
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
        <TableRow className={classes.tableRow} hover key={resource.id}>
          <TableCell>
            {resource.id}
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
      <TextField required className={classes.textField} name="ResourceLink"  label="Resource Link" placeholder="ResourceLink"/>
      <TextField required className={classes.textField} name="ResourceName"  label="Resource Name" placeholder="ResourceName" />
      <Button variant="contained" color="primary">Submit</Button>
    </Box>
  </form>
  </>  
  );
}

export default Resources;